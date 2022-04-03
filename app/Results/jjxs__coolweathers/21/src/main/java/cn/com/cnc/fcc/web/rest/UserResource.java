package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.config.Constants;
import cn.com.cnc.fcc.domain.User;
import cn.com.cnc.fcc.repository.UserRepository;
import cn.com.cnc.fcc.security.AuthoritiesConstants;
import cn.com.cnc.fcc.service.MailService;
import cn.com.cnc.fcc.service.UserService;
import cn.com.cnc.fcc.service.dto.UserDTO;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.errors.EmailAlreadyUsedException;
import cn.com.cnc.fcc.web.rest.errors.LoginAlreadyUsedException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util;
@RestController
@RequestMapping("/api")
public class UserResource {

 private  Logger log;

 private  UserService userService;

 private  UserRepository userRepository;

 private  MailService mailService;

public UserResource(UserService userService, UserRepository userRepository, MailService mailService) {
    this.userService = userService;
    this.userRepository = userRepository;
    this.mailService = mailService;
}
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
@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
public ResponseEntity<Void> deleteUser(String login){
    log.debug("REST request to delete User: {}", login);
    userService.deleteUser(login);
    return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
}


@PutMapping("/users")
@Timed
@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
public ResponseEntity<UserDTO> updateUser(UserDTO userDTO){
    log.debug("REST request to update User : {}", userDTO);
    Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
        throw new EmailAlreadyUsedException();
    }
    existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
    if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
        throw new LoginAlreadyUsedException();
    }
    Optional<UserDTO> updatedUser = userService.updateUser(userDTO);
    return ResponseUtil.wrapOrNotFound(updatedUser, HeaderUtil.createAlert("userManagement.updated", userDTO.getLogin()));
}


@PostMapping("/users")
@Timed
@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
public ResponseEntity<User> createUser(UserDTO userDTO){
    log.debug("REST request to save User : {}", userDTO);
    if (userDTO.getId() != null) {
        throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
    // Lowercase the user login before comparing with database
    } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
        throw new LoginAlreadyUsedException();
    } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
        throw new EmailAlreadyUsedException();
    } else {
        User newUser = userService.createUser(userDTO);
        mailService.sendCreationEmail(newUser);
        return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin())).headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin())).body(newUser);
    }
}


@GetMapping("/users/authorities")
@Timed
@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
public List<String> getAuthorities(){
    return userService.getAuthorities();
}


}