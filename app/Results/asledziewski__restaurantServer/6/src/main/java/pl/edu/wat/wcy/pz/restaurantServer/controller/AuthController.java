package pl.edu.wat.wcy.pz.restaurantServer.controller;
 import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.wcy.pz.restaurantServer.email.MailService;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Role;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import pl.edu.wat.wcy.pz.restaurantServer.form.LoginForm;
import pl.edu.wat.wcy.pz.restaurantServer.form.SignUpForm;
import pl.edu.wat.wcy.pz.restaurantServer.form.response.JwtResponse;
import pl.edu.wat.wcy.pz.restaurantServer.repository.RoleRepository;
import pl.edu.wat.wcy.pz.restaurantServer.repository.UserRepository;
import pl.edu.wat.wcy.pz.restaurantServer.security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RoleRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.JwtProvider;
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

 private  Logger LOGGER;

 private  AuthenticationManager authenticationManager;

 private  UserRepository userRepository;

 private  RoleRepository roleRepository;

 private  MailService mailService;

 private  PasswordEncoder encoder;

 private  JwtProvider jwtProvider;


@PostMapping("/login")
public ResponseEntity<?> authenticateUser(LoginForm loginForm){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getMail(), loginForm.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateJwtToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    LOGGER.info("Logged user: " + principal.getUsername() + ". Authorities: " + principal.getAuthorities().toString());
    Optional<User> user = userRepository.findByMail(principal.getUsername());
    if (user.isPresent()) {
        return ResponseEntity.ok(new JwtResponse(jwt, user.get()));
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid login data.");
    }
}


@PostMapping("/register")
public void createUser(SignUpForm signUpForm){
    if (userRepository.existsByMail(signUpForm.getMail())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with this email already exists.");
    }
    Set<Role> roles = new HashSet<>();
    signUpForm.getRoles().forEach(roleName -> {
        if (roleName.equalsIgnoreCase(roleName)) {
            Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Role not found: " + roleName));
            roles.add(role);
        }
    });
    User user = new User(signUpForm.getMail(), signUpForm.getFirstName(), signUpForm.getLastName(), encoder.encode(signUpForm.getPassword()), roles);
    userRepository.save(user);
    mailService.sendEmail(user.getMail(), "Welcome to Restaurant!", "Hello " + user.getFirstName() + ", thanks for using our system!");
    throw new ResponseStatusException(HttpStatus.CREATED, "Registration successful");
}


}