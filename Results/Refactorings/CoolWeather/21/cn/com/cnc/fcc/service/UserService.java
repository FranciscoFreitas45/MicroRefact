import cn.com.cnc.fcc.config.Constants;
import cn.com.cnc.fcc.domain.Authority;
import cn.com.cnc.fcc.domain.User;
import cn.com.cnc.fcc.repository.AuthorityRepository;
import cn.com.cnc.fcc.repository.UserRepository;
import cn.com.cnc.fcc.security.AuthoritiesConstants;
import cn.com.cnc.fcc.security.SecurityUtils;
import cn.com.cnc.fcc.service.dto.UserDTO;
import cn.com.cnc.fcc.service.util.RandomUtil;
import cn.com.cnc.fcc.web.rest.errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util;
import java.util.stream.Collectors;
@Service
@Transactional
public class UserService {

 private  Logger log;

 private  UserRepository userRepository;

 private  PasswordEncoder passwordEncoder;

 private  AuthorityRepository authorityRepository;

 private  CacheManager cacheManager;


@Transactional(readOnly = true)
public Optional<User> getUserWithAuthorities(){
    return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
}


public Optional<UserDTO> updateUser(UserDTO userDTO){
    return Optional.of(userRepository.findById(userDTO.getId())).filter(Optional::isPresent).map(Optional::get).map(user -> {
        this.clearUserCaches(user);
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(userDTO.isActivated());
        user.setLangKey(userDTO.getLangKey());
        Set<Authority> managedAuthorities = user.getAuthorities();
        managedAuthorities.clear();
        userDTO.getAuthorities().stream().map(authorityRepository::findById).filter(Optional::isPresent).map(Optional::get).forEach(managedAuthorities::add);
        this.clearUserCaches(user);
        log.debug("Changed Information for User: {}", user);
        return user;
    }).map(UserDTO::new);
}


public boolean removeNonActivatedUser(User existingUser){
    if (existingUser.getActivated()) {
        return false;
    }
    userRepository.delete(existingUser);
    userRepository.flush();
    this.clearUserCaches(existingUser);
    return true;
}


public List<String> getAuthorities(){
    return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
}


public void changePassword(String currentClearTextPassword,String newPassword){
    SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(user -> {
        String currentEncryptedPassword = user.getPassword();
        if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
            throw new InvalidPasswordException();
        }
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        this.clearUserCaches(user);
        log.debug("Changed password for User: {}", user);
    });
}


public Optional<User> activateRegistration(String key){
    log.debug("Activating user for activation key {}", key);
    return userRepository.findOneByActivationKey(key).map(user -> {
        // activate given user for the registration key.
        user.setActivated(true);
        user.setActivationKey(null);
        this.clearUserCaches(user);
        log.debug("Activated user: {}", user);
        return user;
    });
}


@Transactional(readOnly = true)
public Page<UserDTO> getAllManagedUsers(Pageable pageable){
    return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
}


public void clearUserCaches(User user){
    Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
    Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
}


@Transactional(readOnly = true)
public Optional<User> getUserWithAuthoritiesByLogin(String login){
    return userRepository.findOneWithAuthoritiesByLogin(login);
}


public User registerUser(UserDTO userDTO,String password){
    userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
        boolean removed = removeNonActivatedUser(existingUser);
        if (!removed) {
            throw new LoginAlreadyUsedException();
        }
    });
    userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
        boolean removed = removeNonActivatedUser(existingUser);
        if (!removed) {
            throw new EmailAlreadyUsedException();
        }
    });
    User newUser = new User();
    String encryptedPassword = passwordEncoder.encode(password);
    newUser.setLogin(userDTO.getLogin().toLowerCase());
    // new user gets initially a generated password
    newUser.setPassword(encryptedPassword);
    newUser.setFirstName(userDTO.getFirstName());
    newUser.setLastName(userDTO.getLastName());
    newUser.setEmail(userDTO.getEmail().toLowerCase());
    newUser.setImageUrl(userDTO.getImageUrl());
    newUser.setLangKey(userDTO.getLangKey());
    // new user is not active
    newUser.setActivated(false);
    // new user gets registration key
    newUser.setActivationKey(RandomUtil.generateActivationKey());
    Set<Authority> authorities = new HashSet<>();
    authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
    newUser.setAuthorities(authorities);
    userRepository.save(newUser);
    this.clearUserCaches(newUser);
    log.debug("Created Information for User: {}", newUser);
    return newUser;
}


public void deleteUser(String login){
    userRepository.findOneByLogin(login).ifPresent(user -> {
        userRepository.delete(user);
        this.clearUserCaches(user);
        log.debug("Deleted User: {}", user);
    });
}


@Scheduled(cron = "0 0 1 * * ?")
public void removeNotActivatedUsers(){
    userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS)).forEach(user -> {
        log.debug("Deleting not activated user {}", user.getLogin());
        userRepository.delete(user);
        this.clearUserCaches(user);
    });
}


public User createUser(UserDTO userDTO){
    User user = new User();
    user.setLogin(userDTO.getLogin().toLowerCase());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail().toLowerCase());
    user.setImageUrl(userDTO.getImageUrl());
    if (userDTO.getLangKey() == null) {
        // default language
        user.setLangKey(Constants.DEFAULT_LANGUAGE);
    } else {
        user.setLangKey(userDTO.getLangKey());
    }
    String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
    user.setPassword(encryptedPassword);
    user.setResetKey(RandomUtil.generateResetKey());
    user.setResetDate(Instant.now());
    user.setActivated(true);
    if (userDTO.getAuthorities() != null) {
        Set<Authority> authorities = userDTO.getAuthorities().stream().map(authorityRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
        user.setAuthorities(authorities);
    }
    userRepository.save(user);
    this.clearUserCaches(user);
    log.debug("Created Information for User: {}", user);
    return user;
}


public Optional<User> completePasswordReset(String newPassword,String key){
    log.debug("Reset user password for reset key {}", key);
    return userRepository.findOneByResetKey(key).filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400))).map(user -> {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        this.clearUserCaches(user);
        return user;
    });
}


public Optional<User> requestPasswordReset(String mail){
    return userRepository.findOneByEmailIgnoreCase(mail).filter(User::getActivated).map(user -> {
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        this.clearUserCaches(user);
        return user;
    });
}


}