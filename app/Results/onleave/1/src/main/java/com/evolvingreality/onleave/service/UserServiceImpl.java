package com.evolvingreality.onleave.service;
 import com.evolvingreality.onleave.model.SecurityGroup;
import com.evolvingreality.onleave.model.SecurityGroupMember;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.security.SecurityUtils;
import com.evolvingreality.onleave.service.util.RandomUtil;
import com.evolvingreality.onleave.web.rest.dto.UserDTO;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends EntityServiceImpl<User>implements UserService{

 private  Logger log;

 private  UserRepository userRepository;

 private  SecurityGroupService securityGroupService;

 private  PasswordEncoder passwordEncoder;

@Autowired
public UserServiceImpl(final UserRepository userRepository, final SecurityGroupService securityGroupService, final PasswordEncoder passwordEncoder) {
    super(userRepository);
    this.userRepository = userRepository;
    this.securityGroupService = securityGroupService;
    this.passwordEncoder = passwordEncoder;
}
@Override
public Optional<User> activateRegistration(String key){
    log.debug("Activating user for activation key {}", key);
    userRepository.findOneByActivationKey(key).map(user -> {
        // activate given user for the registration key.
        user.setActivated(true);
        user.setActivationKey(null);
        userRepository.save(user);
        log.debug("Activated user: {}", user);
        return user;
    });
    return Optional.empty();
}


@Override
public void updateUserInformation(String firstName,String lastName,String email,String langKey){
    userRepository.findOneByEmail(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setLangKey(langKey);
        userRepository.save(u);
        log.debug("Changed Information for User: {}", u);
    });
}


@Override
@Transactional(readOnly = true)
public User getUserWithAuthorities(){
    User currentUser = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin()).get();
    currentUser.getGroupMembers().stream().forEach(// eagerly load the association
    member -> member.getSecurityGroup().getAuthorities().size());
    return currentUser;
}


@Override
@Transactional(readOnly = false)
public User create(UserDTO userDto){
    User newUser = new User();
    String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
    // new user gets initially a generated password
    newUser.setPassword(encryptedPassword);
    newUser.setFirstName(userDto.getFirstName());
    newUser.setLastName(userDto.getLastName());
    newUser.setEmail(userDto.getEmail());
    newUser.setLangKey(userDto.getLangKey());
    // new user is not active
    newUser.setActivated(false);
    // new user gets registration key
    newUser.setActivationKey(RandomUtil.generateActivationKey());
    SecurityGroup securityGroup = securityGroupService.get(userDto.getSecurityGroupId()).get();
    SecurityGroupMember securityGroupMember = new SecurityGroupMember(newUser, securityGroup);
    newUser.getGroupMembers().add(securityGroupMember);
    save(newUser);
    log.debug("Created Information for User: {}", newUser);
    return newUser;
}


@Override
@Scheduled(cron = "0 0 1 * * ?")
public void removeNotActivatedUsers(){
    DateTime now = new DateTime();
    List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
    for (User user : users) {
        log.debug("Deleting not activated user {}", user.getEmail());
        userRepository.delete(user);
    }
}


@Override
public Optional<User> completePasswordReset(String newPassword,String key){
    log.debug("Reset user password for reset key {}", key);
    return userRepository.findOneByResetKey(key).filter(user -> {
        DateTime oneDayAgo = DateTime.now().minusHours(24);
        return user.getResetDate().isAfter(oneDayAgo.toInstant().getMillis());
    }).map(user -> {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        userRepository.save(user);
        return user;
    });
}


@Override
public Optional<User> requestPasswordReset(String mail){
    return userRepository.findOneByEmail(mail).filter(user -> user.getActivated() == true).map(user -> {
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(DateTime.now());
        userRepository.save(user);
        return user;
    });
}


@Override
public void changePassword(String password){
    userRepository.findOneByEmail(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
        String encryptedPassword = passwordEncoder.encode(password);
        u.setPassword(encryptedPassword);
        userRepository.save(u);
        log.debug("Changed password for User: {}", u);
    });
}


}