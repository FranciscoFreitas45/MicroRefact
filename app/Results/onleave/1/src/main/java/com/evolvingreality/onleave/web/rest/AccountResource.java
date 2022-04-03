package com.evolvingreality.onleave.web.rest;
 import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.model.SecurityGroupAuthority;
import com.evolvingreality.onleave.model.SecurityGroupMember;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.security.SecurityUtils;
import com.evolvingreality.onleave.service.MailService;
import com.evolvingreality.onleave.service.UserService;
import com.evolvingreality.onleave.web.rest.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class AccountResource {

 private  Logger log;

@Inject
 private  UserRepository userRepository;

@Inject
 private  UserService userService;

@Inject
 private  MailService mailService;


@RequestMapping(value = "/account/reset_password/finish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<String> finishPasswordReset(String key,String newPassword){
    if (!checkPasswordLength(newPassword)) {
        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
    return userService.completePasswordReset(newPassword, key).map(user -> new ResponseEntity<String>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<UserDTO> getAccount(){
    return Optional.ofNullable(userService.getUserWithAuthorities()).map(user -> {
        List<String> authorities = new ArrayList<>();
        for (SecurityGroupMember securityGroupMember : user.getGroupMembers()) {
            authorities.addAll(securityGroupMember.getSecurityGroup().getAuthorities().stream().map(SecurityGroupAuthority::getAuthority).collect(Collectors.toList()));
        }
        return new ResponseEntity<>(new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getLangKey(), authorities), HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


public boolean checkPasswordLength(String password){
    return (!StringUtils.isEmpty(password) && password.length() >= 8 && password.length() <= 8);
}


@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public String isAuthenticated(HttpServletRequest request){
    log.debug("REST request to check if the current user is authenticated");
    return request.getRemoteUser();
}


@RequestMapping(value = "/account/reset_password/init", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
@Timed
public ResponseEntity<?> requestPasswordReset(String mail,HttpServletRequest request){
    return userService.requestPasswordReset(mail).map(user -> {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        mailService.sendPasswordResetMail(user, baseUrl);
        return new ResponseEntity<>("e-mail was sent", HttpStatus.OK);
    }).orElse(new ResponseEntity<>("e-mail address not registered", HttpStatus.BAD_REQUEST));
}


@RequestMapping(value = "/activate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<String> activateAccount(String key){
    return Optional.ofNullable(userService.activateRegistration(key)).map(user -> new ResponseEntity<String>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
}


@RequestMapping(value = "/account/change_password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<?> changePassword(String password){
    if (!checkPasswordLength(password)) {
        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
    userService.changePassword(password);
    return new ResponseEntity<>(HttpStatus.OK);
}


}