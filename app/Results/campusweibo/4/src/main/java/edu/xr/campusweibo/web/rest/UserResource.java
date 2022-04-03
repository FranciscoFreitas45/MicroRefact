package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.Constants;
import com.codahale.metrics.annotation.Timed;
import edu.xr.campusweibo.domain.User;
import edu.xr.campusweibo.repository.UserRepository;
import edu.xr.campusweibo.security.AuthoritiesConstants;
import edu.xr.campusweibo.service.MailService;
import edu.xr.campusweibo.service.UserService;
import edu.xr.campusweibo.service.dto.UserDTO;
import edu.xr.campusweibo.web.rest.vm.ManagedUserVM;
import edu.xr.campusweibo.web.rest.util.HeaderUtil;
import edu.xr.campusweibo.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util;
@RestController
@RequestMapping("/api")
public class UserResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  UserRepository userRepository;

 private  MailService mailService;

 private  UserService userService;


@GetMapping("/users")
@Timed
public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable){
    final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
@Timed
public ResponseEntity<UserDTO> getUser(String login){
    log.debug("REST request to get User : {}", login);
    return ResponseUtil.wrapOrNotFound(userService.getUserWithAuthoritiesByLogin(login).map(UserDTO::new));
}


@DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
@Timed
@Secured(AuthoritiesConstants.ADMIN)
public ResponseEntity<Void> deleteUser(String login){
    log.debug("REST request to delete User: {}", login);
    userService.deleteUser(login);
    return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
}


@PutMapping("/users")
@Timed
@Secured(AuthoritiesConstants.ADMIN)
public ResponseEntity<UserDTO> updateUser(ManagedUserVM managedUserVM){
    log.debug("REST request to update User : {}", managedUserVM);
    Optional<User> existingUser = userRepository.findOneByEmail(managedUserVM.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserVM.getId()))) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "E-mail already in use")).body(null);
    }
    existingUser = userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase());
    if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserVM.getId()))) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "userexists", "Login already in use")).body(null);
    }
    Optional<UserDTO> updatedUser = userService.updateUser(managedUserVM);
    return ResponseUtil.wrapOrNotFound(updatedUser, HeaderUtil.createAlert("userManagement.updated", managedUserVM.getLogin()));
}


@PostMapping("/users")
@Timed
@Secured(AuthoritiesConstants.ADMIN)
public ResponseEntity createUser(ManagedUserVM managedUserVM){
    log.debug("REST request to save User : {}", managedUserVM);
    // Lowercase the user login before comparing with database
    if (userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).isPresent()) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "userexists", "Login already in use")).body(null);
    } else if (userRepository.findOneByEmail(managedUserVM.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use")).body(null);
    } else {
        User newUser = userService.createUser(managedUserVM);
        mailService.sendCreationEmail(newUser);
        return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin())).headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin())).body(newUser);
    }
}


}