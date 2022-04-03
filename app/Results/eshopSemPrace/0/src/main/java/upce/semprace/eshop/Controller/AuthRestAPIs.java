package upce.semprace.eshop.Controller;
 import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upce.semprace.eshop.message.request.LoginForm;
import upce.semprace.eshop.message.request.SignUpForm;
import upce.semprace.eshop.message.response.JwtResponse;
import upce.semprace.eshop.message.response.ResponseMessage;
import upce.semprace.eshop.entity.Role;
import upce.semprace.eshop.entity.RoleName;
import upce.semprace.eshop.entity.Uzivatel;
import upce.semprace.eshop.repository.RoleRepository;
import upce.semprace.eshop.repository.UzivatelRepository;
import upce.semprace.eshop.security.jwt.JwtProvider;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.Interface.RoleRepository;
import upce.semprace.eshop.Interface.JwtProvider;
import upce.semprace.eshop.DTO.Uzivatel;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private UzivatelRepository userRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private PasswordEncoder encoder;

@Autowired
 private JwtProvider jwtProvider;


@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(LoginForm loginRequest){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateJwtToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
}


@PostMapping("/signup")
public ResponseEntity<?> registerUser(SignUpForm signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
    }
    Uzivatel user = new Uzivatel(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
    Set<Role> roles = new HashSet<>();
    if (signUpRequest.getRole() != null) {
        Set<String> strRoles = signUpRequest.getRole();
        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
    } else {
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
    }
    user.setJmeno(signUpRequest.getUsername());
    user.setPrijmeni(signUpRequest.getUsername());
    user.setAdresa(signUpRequest.getEmail());
    user.setRoles(roles);
    userRepository.save(user);
    return new ResponseEntity<>(new ResponseMessage("User " + signUpRequest.getFirstname() + " is registered successfully!"), HttpStatus.OK);
}


}