package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.domain.User;
import cn.com.cnc.fcc.repository.UserRepository;
import cn.com.cnc.fcc.security.SecurityUtils;
import cn.com.cnc.fcc.service.MailService;
import cn.com.cnc.fcc.service.UserService;
import cn.com.cnc.fcc.service.dto.PasswordChangeDTO;
import cn.com.cnc.fcc.service.dto.UserDTO;
import cn.com.cnc.fcc.web.rest.errors;
import cn.com.cnc.fcc.web.rest.vm.KeyAndPasswordVM;
import cn.com.cnc.fcc.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util;
@RestController
@RequestMapping("/api")
public class AccountResource {

 private  Logger log;

 private  UserRepository userRepository;

 private  UserService userService;

 private  MailService mailService;

 private  EntityManagerFactory emf;

public AccountResource(UserRepository userRepository, UserService userService, MailService mailService, EntityManagerFactory emf) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.mailService = mailService;
    this.emf = emf;
}
@PostMapping(path = "/account/reset-password/finish")
@Timed
public void finishPasswordReset(KeyAndPasswordVM keyAndPassword){
    if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
        throw new InvalidPasswordException();
    }
    Optional<User> user = userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());
    if (!user.isPresent()) {
        throw new InternalServerErrorException("No user was found for this reset key");
    }
}


@PostMapping("/register")
@Timed
@ResponseStatus(HttpStatus.CREATED)
public void registerAccount(ManagedUserVM managedUserVM){
    if (!checkPasswordLength(managedUserVM.getPassword())) {
        throw new InvalidPasswordException();
    }
    User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
    mailService.sendActivationEmail(user);
}


@GetMapping("/account")
@Timed
public UserDTO getAccount(){
    UserDTO account = new UserDTO();
    EntityManager em = emf.createEntityManager();
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("anonymousUser" == authentication.getPrincipal()) {
            em.close();
            return null;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Query query = em.createNativeQuery("select user.* from rbac_user user where user.user_code = :userCode and del_flag = 0", RbacUser.class);
        query.setParameter("userCode", user.getUsername());
        List<RbacUser> rbacUser = query.getResultList();
        if (null == rbacUser || rbacUser.size() == 0) {
            em.close();
            return null;
        }
        Set<String> actionset = new HashSet<String>();
        actionset.add("ROLE_USER");
        if (rbacUser.get(0).getAppId() == null) {
            actionset.add("ROLE_ADMIN");
        }
        account.setLangKey("zh-cn");
        account.setRbacUser(rbacUser);
        account.setAuthorities(actionset);
        account.setFirstName(rbacUser.get(0).getUserName());
        account.setLastName(rbacUser.get(0).getUserName());
        account.setEmail(rbacUser.get(0).getUserMail());
        account.setActivated(true);
        account.setLogin(rbacUser.get(0).getUserCode());
        account.setId(rbacUser.get(0).getId());
        query = em.createNativeQuery(getMenuSQL(), RbacMenu.class);
        query.setParameter("userCode", user.getUsername());
        List<RbacMenu> rbacMenus = query.getResultList();
        account.setRbacMenus(rbacMenus);
        em.close();
        query = null;
    } catch (Exception e) {
        em.close();
        throw new InternalServerErrorException("User could not be found");
    }
    return account;
}


public boolean checkPasswordLength(String password){
    return !StringUtils.isEmpty(password) && password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH && password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
}


@GetMapping("/authenticate")
@Timed
public String isAuthenticated(HttpServletRequest request){
    log.debug("REST request to check if the current user is authenticated");
    return request.getRemoteUser();
}


public String getMenuSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT                                                                          ");
    objSqlContent.append(" 	rm.*                                                                           ");
    objSqlContent.append(" FROM                                                                            ");
    objSqlContent.append(" 	rbac_menu rm                                                                   ");
    objSqlContent.append(" INNER JOIN rbac_menu_right_relation rmrr ON rm.id = rmrr.menu_id                ");
    objSqlContent.append(" AND rmrr.del_flag = 0                                                           ");
    objSqlContent.append(" AND rmrr.stop_flag = 0                                                          ");
    objSqlContent.append(" INNER JOIN rbac_role_right_relation rrrr ON rmrr.right_id = rrrr.right_id       ");
    objSqlContent.append(" AND rrrr.del_flag = 0                                                           ");
    objSqlContent.append(" AND rrrr.stop_flag = 0                                                          ");
    objSqlContent.append(" INNER JOIN rbac_user_right_relation rurr ON rrrr.role_id = rurr.role_id         ");
    objSqlContent.append(" AND rurr.del_flag = 0                                                           ");
    objSqlContent.append(" AND rurr.stop_flag = 0                                                          ");
    objSqlContent.append(" INNER JOIN rbac_user ru ON rurr.user_id = ru.id                                 ");
    objSqlContent.append(" AND ru.del_flag = 0                                                             ");
    objSqlContent.append(" AND ru.stop_flag = 0                                                            ");
    objSqlContent.append(" WHERE                                                                           ");
    objSqlContent.append(" 	ru.user_code = :userCode                                                       ");
    objSqlContent.append(" AND rm.del_flag = 0                                                             ");
    objSqlContent.append(" AND rm.stop_flag = 0                                                            ");
    objSqlContent.append(" order by rm.id                                                                  ");
    return objSqlContent.toString();
}


@PostMapping(path = "/account/reset-password/init")
@Timed
public void requestPasswordReset(String mail){
    mailService.sendPasswordResetMail(userService.requestPasswordReset(mail).orElseThrow(EmailNotFoundException::new));
}


@GetMapping("/activate")
@Timed
public void activateAccount(String key){
    Optional<User> user = userService.activateRegistration(key);
    if (!user.isPresent()) {
        throw new InternalServerErrorException("No user was found for this activation key");
    }
}


@PostMapping("/account")
@Timed
public void saveAccount(UserDTO userDTO){
    final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
    Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
        throw new EmailAlreadyUsedException();
    }
    Optional<User> user = userRepository.findOneByLogin(userLogin);
    if (!user.isPresent()) {
        throw new InternalServerErrorException("User could not be found");
    }
    userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getLangKey(), userDTO.getImageUrl());
}


@PostMapping(path = "/account/change-password")
@Timed
public void changePassword(PasswordChangeDTO passwordChangeDto){
    if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
        throw new InvalidPasswordException();
    }
    userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
}


}