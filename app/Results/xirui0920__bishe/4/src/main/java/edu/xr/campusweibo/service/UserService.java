package edu.xr.campusweibo.service;
 import edu.xr.campusweibo.domain.Authority;
import edu.xr.campusweibo.domain.User;
import edu.xr.campusweibo.repository.AuthorityRepository;
import edu.xr.campusweibo.repository.PersistentTokenRepository;
import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.repository.UserRepository;
import edu.xr.campusweibo.security.AuthoritiesConstants;
import edu.xr.campusweibo.security.SecurityUtils;
import edu.xr.campusweibo.service.util.RandomUtil;
import edu.xr.campusweibo.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util;
import edu.xr.campusweibo.Interface.PersistentTokenRepository;
@Service
@Transactional
public class UserService {

 private  Logger log;

 private  UserRepository userRepository;

 private  PasswordEncoder passwordEncoder;

 private  PersistentTokenRepository persistentTokenRepository;

 private  AuthorityRepository authorityRepository;

public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PersistentTokenRepository persistentTokenRepository, AuthorityRepository authorityRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.persistentTokenRepository = persistentTokenRepository;
    this.authorityRepository = authorityRepository;
}
public Optional<User> activateRegistration(String key){
    log.debug("Activating user for activation key {}", key);
    return userRepository.findOneByActivationKey(key).map(user -> {
        // activate given user for the registration key.
        user.setActivated(true);
        user.setActivationKey(null);
        log.debug("Activated user: {}", user);
        return user;
    });
}


@Transactional(readOnly = true)
public Page<UserDTO> getAllManagedUsers(Pageable pageable){
    return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
}


@Transactional(readOnly = true)
public Optional<User> getUserWithAuthoritiesByLogin(String login){
    return userRepository.findOneWithAuthoritiesByLogin(login);
}


@Transactional(readOnly = true)
public User getUserWithAuthorities(){
    return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).orElse(null);
}


public void deleteUser(String login){
    userRepository.findOneByLogin(login).ifPresent(user -> {
        userRepository.delete(user);
        log.debug("Deleted User: {}", user);
    });
}


public Optional<UserDTO> updateUser(UserDTO userDTO){
    return Optional.of(userRepository.findOne(userDTO.getId())).map(user -> {
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(userDTO.isActivated());
        user.setLangKey(userDTO.getLangKey());
        Set<Authority> managedAuthorities = user.getAuthorities();
        managedAuthorities.clear();
        userDTO.getAuthorities().stream().map(authorityRepository::findOne).forEach(managedAuthorities::add);
        log.debug("Changed Information for User: {}", user);
        return user;
    }).map(UserDTO::new);
}


@Scheduled(cron = "0 0 1 * * ?")
public void removeNotActivatedUsers(){
    ZonedDateTime now = ZonedDateTime.now();
    List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
    for (User user : users) {
        log.debug("Deleting not activated user {}", user.getLogin());
        userRepository.delete(user);
    }
}


public User createUser(UserDTO userDTO){
    User user = new User();
    user.setLogin(userDTO.getLogin());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    user.setImageUrl(userDTO.getImageUrl());
    if (userDTO.getLangKey() == null) {
        // default language
        user.setLangKey("zh-cn");
    } else {
        user.setLangKey(userDTO.getLangKey());
    }
    if (userDTO.getAuthorities() != null) {
        Set<Authority> authorities = new HashSet<>();
        userDTO.getAuthorities().forEach(authority -> authorities.add(authorityRepository.findOne(authority)));
        user.setAuthorities(authorities);
    }
    String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
    user.setPassword(encryptedPassword);
    user.setResetKey(RandomUtil.generateResetKey());
    user.setResetDate(ZonedDateTime.now());
    user.setActivated(true);
    userRepository.save(user);
    log.debug("Created Information for User: {}", user);
    return user;
}


@Scheduled(cron = "0 0 0 * * ?")
public void removeOldPersistentTokens(){
    LocalDate now = LocalDate.now();
    persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).forEach(token -> {
        log.debug("Deleting token {}", token.getSeries());
        User user = token.getUser();
        user.getPersistentTokens().remove(token);
        persistentTokenRepository.delete(token);
    });
}


public Optional<User> completePasswordReset(String newPassword,String key){
    log.debug("Reset user password for reset key {}", key);
    return userRepository.findOneByResetKey(key).filter(user -> {
        ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
        return user.getResetDate().isAfter(oneDayAgo);
    }).map(user -> {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        return user;
    });
}


public Optional<User> requestPasswordReset(String mail){
    return userRepository.findOneByEmail(mail).filter(User::getActivated).map(user -> {
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(ZonedDateTime.now());
        return user;
    });
}


public void changePassword(String password){
    userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        log.debug("Changed password for User: {}", user);
    });
}


}