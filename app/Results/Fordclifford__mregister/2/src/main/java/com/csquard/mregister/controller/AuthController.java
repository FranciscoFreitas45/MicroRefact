package com.csquard.mregister.controller;
 import com.csquard.mregister.exception.AppException;
import com.csquard.mregister.exception.ResourceNotFoundException;
import com.csquard.mregister.model.Roles;
import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.model.Tdr;
import com.csquard.mregister.model.Asm;
import com.csquard.mregister.model.RoleName;
import com.csquard.mregister.model.User;
import com.csquard.mregister.payload.ApiResponse;
import com.csquard.mregister.payload.JwtAuthenticationResponse;
import com.csquard.mregister.payload.LoginRequest;
import com.csquard.mregister.payload.SignUpRequest;
import com.csquard.mregister.repository.AsmRepository;
import com.csquard.mregister.repository.RoleRepository;
import com.csquard.mregister.repository.SalesAreaRepository;
import com.csquard.mregister.repository.SalesRegionRepository;
import com.csquard.mregister.repository.TdrRepository;
import com.csquard.mregister.repository.UserRepository;
import com.csquard.mregister.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import com.csquard.mregister.Interface.UserRepository;
import com.csquard.mregister.Interface.RoleRepository;
import com.csquard.mregister.Interface.AsmRepository;
import com.csquard.mregister.Interface.JwtTokenProvider;
import com.csquard.mregister.DTO.User;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private UserRepository userRepository;

@Autowired
 private TdrRepository tdrRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private AsmRepository asmRepository;

@Autowired
 private SalesAreaRepository salesAreaRepository;

@Autowired
 private SalesRegionRepository salesRegionRepository;

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private JwtTokenProvider tokenProvider;


@PostMapping("/signup/asm")
@PreAuthorize("hasAnyRole('ADMIN','DISTRIBUTOR')")
public ResponseEntity<?> registerAsm(SignUpRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }
    if (asmRepository.existsBySalesRegionId(signUpRequest.getSales_region_id())) {
        return new ResponseEntity(new ApiResponse(false, "Sorry the sales region you selected already assigned ASM!"), HttpStatus.BAD_REQUEST);
    }
    // Creating user's account
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Roles userRole = roleRepository.findByName(RoleName.ROLE_ASM).orElseThrow(() -> new AppException("User Role not set."));
    user.setRoles(Collections.singleton(userRole));
    User result = userRepository.save(user);
    salesRegionRepository.findById(signUpRequest.getSales_region_id()).orElseThrow(() -> new ResourceNotFoundException("SalesRegion", "id", signUpRequest.getSales_region_id()));
    Asm asm = new Asm(signUpRequest.getFirst_name(), signUpRequest.getLast_name(), signUpRequest.getId_no(), result.getId(), signUpRequest.getSales_region_id());
    asmRepository.save(asm);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
    return ResponseEntity.created(location).body(new ApiResponse(true, "Asm registered successfully"));
}


@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
}


@PostMapping("/signup/admin")
public ResponseEntity<?> registerAdmin(SignUpRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }
    // Creating user's account
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Roles userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User Role not set."));
    user.setRoles(Collections.singleton(userRole));
    User result = userRepository.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
    return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully"));
}


@PostMapping("/signup/tdr")
@PreAuthorize("hasAnyRole('ASM','ADMIN','DISTRIBUTOR')")
public ResponseEntity<?> registerTdr(SignUpRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }
    if (tdrRepository.existsBySalesAreaId(signUpRequest.getSales_area_id())) {
        return new ResponseEntity(new ApiResponse(false, "Sorry the sales area you selected already assigned Tdr!"), HttpStatus.BAD_REQUEST);
    }
    // Creating tdr's account
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Roles userRole = roleRepository.findByName(RoleName.ROLE_TDR).orElseThrow(() -> new AppException("User Role not set."));
    user.setRoles(Collections.singleton(userRole));
    asmRepository.findById(signUpRequest.getAsm_id()).orElseThrow(() -> new AppException("Asm not found"));
    salesAreaRepository.findById(signUpRequest.getSales_area_id()).orElseThrow(() -> new ResourceNotFoundException("SalesArea", "id", signUpRequest.getSales_area_id()));
    User result = userRepository.save(user);
    Tdr tdr = new Tdr(signUpRequest.getFirst_name(), signUpRequest.getLast_name(), signUpRequest.getId_no(), result.getId(), signUpRequest.getSales_area_id(), signUpRequest.getAsm_id());
    tdrRepository.save(tdr);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
    return ResponseEntity.created(location).body(new ApiResponse(true, "Tdr successfully registered"));
}


@PostMapping("/signup/distributor")
@PreAuthorize("hasAnyRole('ADMIN')")
public ResponseEntity<?> registerDistributor(SignUpRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }
    // Creating user's account
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Roles userRole = roleRepository.findByName(RoleName.ROLE_DISTRIBUTOR).orElseThrow(() -> new AppException("User Role not set."));
    user.setRoles(Collections.singleton(userRole));
    User result = userRepository.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
    return ResponseEntity.created(location).body(new ApiResponse(true, "Distributor registered successfully"));
}


}