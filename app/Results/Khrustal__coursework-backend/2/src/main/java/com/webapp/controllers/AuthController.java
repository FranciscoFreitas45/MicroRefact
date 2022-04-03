package com.webapp.controllers;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.webapp.models.ERole;
import com.webapp.models.Role;
import com.webapp.models.User;
import com.webapp.payload.request.LoginRequest;
import com.webapp.payload.request.SignupRequest;
import com.webapp.payload.response.JwtResponse;
import com.webapp.payload.response.MessageResponse;
import com.webapp.repository.RoleRepository;
import com.webapp.repository.UserRepository;
import com.webapp.security.jwt.JwtUtils;
import com.webapp.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private UserRepository userRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private PasswordEncoder encoder;

@Autowired
 private JwtUtils jwtUtils;


@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
}


@PostMapping("/signup")
public ResponseEntity<?> registerUser(SignupRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }
    // Create new user's account
    User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
}


}