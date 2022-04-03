package com.evolvingreality.onleave.web.rest;
 import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.MailService;
import com.evolvingreality.onleave.service.UserService;
import com.evolvingreality.onleave.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class UserResource {

 private  Logger log;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  UserService userService;

@Autowired
 private  MailService mailService;


@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public Page<User> getAll(Pageable pageable){
    log.debug("REST request to get all Users");
    return userRepository.findAll(pageable);
}


@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> post(UserDTO userDTO,HttpServletRequest request){
    return userRepository.findOneByEmail(userDTO.getEmail()).map(user -> new ResponseEntity<>("login already in use", HttpStatus.BAD_REQUEST)).orElseGet(() -> userRepository.findOneByEmail(userDTO.getEmail()).map(user -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST)).orElseGet(() -> {
        User user = userService.create(userDTO);
        String baseUrl = // "http"
        request.getScheme() + // "://"
        "://" + // "myhost"
        request.getServerName() + // ":"
        ":" + // "80"
        request.getServerPort();
        mailService.sendActivationEmail(user, baseUrl);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }));
}


@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<User> getUser(Long id){
    log.debug("REST request to get User : {}", id);
    return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
}


}