package edu.xr.campusweibo.web.rest;
 import com.codahale.metrics.annotation.Timed;
import edu.xr.campusweibo.domain.PersistentToken;
import edu.xr.campusweibo.domain.User;
import edu.xr.campusweibo.repository.PersistentTokenRepository;
import edu.xr.campusweibo.repository.UserRepository;
import edu.xr.campusweibo.security.SecurityUtils;
import edu.xr.campusweibo.service.MailService;
import edu.xr.campusweibo.service.UserService;
import edu.xr.campusweibo.service.dto.UserDTO;
import edu.xr.campusweibo.web.rest.vm.KeyAndPasswordVM;
import edu.xr.campusweibo.web.rest.vm.ManagedUserVM;
import edu.xr.campusweibo.web.rest.util.HeaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util;
import edu.xr.campusweibo.Interface.PersistentTokenRepository;
@RestController
@RequestMapping("/api")
public class AccountResource {

 private  Logger log;

 private  UserRepository userRepository;

 private  UserService userService;

 private  MailService mailService;

 private  PersistentTokenRepository persistentTokenRepository;

public AccountResource(UserRepository userRepository, UserService userService, MailService mailService, PersistentTokenRepository persistentTokenRepository) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.mailService = mailService;
    this.persistentTokenRepository = persistentTokenRepository;
}
@PostMapping(path = "/account/reset_password/finish", produces = MediaType.TEXT_PLAIN_VALUE)
@Timed
public ResponseEntity<String> finishPasswordReset(KeyAndPasswordVM keyAndPassword){
    if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
    return userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey()).map(user -> new ResponseEntity<String>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@GetMapping("/account/sessions")
@Timed
public ResponseEntity<List<PersistentToken>> getCurrentSessions(){
    return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).map(user -> new ResponseEntity<>(persistentTokenRepository.findByUser(user), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@PostMapping(path = "/register", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
@Timed
public ResponseEntity registerAccount(ManagedUserVM managedUserVM){
    HttpHeaders textPlainHeaders = new HttpHeaders();
    textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
    return userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).map(user -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST)).orElseGet(() -> userRepository.findOneByEmail(managedUserVM.getEmail()).map(user -> new ResponseEntity<>("e-mail address already in use", textPlainHeaders, HttpStatus.BAD_REQUEST)).orElseGet(() -> {
        User user = userService.createUser(managedUserVM.getLogin(), managedUserVM.getPassword(), managedUserVM.getFirstName(), managedUserVM.getLastName(), managedUserVM.getEmail().toLowerCase(), managedUserVM.getImageUrl(), managedUserVM.getLangKey());
        mailService.sendActivationEmail(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }));
}


@GetMapping("/account")
@Timed
public ResponseEntity<UserDTO> getAccount(){
    return Optional.ofNullable(userService.getUserWithAuthorities()).map(user -> new ResponseEntity<>(new UserDTO(user), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
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


@PostMapping(path = "/account/reset_password/init", produces = MediaType.TEXT_PLAIN_VALUE)
@Timed
public ResponseEntity requestPasswordReset(String mail){
    return userService.requestPasswordReset(mail).map(user -> {
        mailService.sendPasswordResetMail(user);
        return new ResponseEntity<>("e-mail was sent", HttpStatus.OK);
    }).orElse(new ResponseEntity<>("e-mail address not registered", HttpStatus.BAD_REQUEST));
}


@GetMapping("/activate")
@Timed
public ResponseEntity<String> activateAccount(String key){
    return userService.activateRegistration(key).map(user -> new ResponseEntity<String>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@PostMapping("/account")
@Timed
public ResponseEntity saveAccount(UserDTO userDTO){
    Optional<User> existingUser = userRepository.findOneByEmail(userDTO.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userDTO.getLogin()))) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("user-management", "emailexists", "Email already in use")).body(null);
    }
    return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).map(u -> {
        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getLangKey());
        return new ResponseEntity(HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@DeleteMapping("/account/sessions/{series}")
@Timed
public void invalidateSession(String series){
    String decodedSeries = URLDecoder.decode(series, "UTF-8");
    userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> persistentTokenRepository.findByUser(u).stream().filter(persistentToken -> StringUtils.equals(persistentToken.getSeries(), decodedSeries)).findAny().ifPresent(t -> persistentTokenRepository.delete(decodedSeries)));
}


@PostMapping(path = "/account/change_password", produces = MediaType.TEXT_PLAIN_VALUE)
@Timed
public ResponseEntity changePassword(String password){
    if (!checkPasswordLength(password)) {
        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
    userService.changePassword(password);
    return new ResponseEntity<>(HttpStatus.OK);
}


}