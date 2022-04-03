package com.yalcin.controller;
 import com.yalcin.dto.request.ForgotPasswordForm;
import com.yalcin.dto.request.LoginForm;
import com.yalcin.dto.request.SignUpForm;
import com.yalcin.dto.response.LoginResponse;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.entity;
import com.yalcin.enums.ErrorCodes;
import com.yalcin.event.OnPasswordForgotEvent;
import com.yalcin.event.OnRegistrationSuccessEvent;
import com.yalcin.exception.AccountNotActivatedException;
import com.yalcin.exception.BadRequestException;
import com.yalcin.exception.ErrorWhileSendingEmailException;
import com.yalcin.repository.ActiveSessionsRepository;
import com.yalcin.repository.AttemptRepository;
import com.yalcin.repository.RoleRepository;
import com.yalcin.repository.UserRepository;
import com.yalcin.security.jwt.JwtProvider;
import com.yalcin.security.services.UserDetailImpl;
import com.yalcin.service.AuthenticatorService;
import com.yalcin.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.RoleRepository;
import com.yalcin.DTO.SuccessResponse;
import com.yalcin.DTO.ActiveSessions;
@RestController
@RequestMapping("ecommerce/authenticator")
public class AuthenticatorController {

@Autowired
 private AuthenticatorService authenticatorService;

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private UserRepository userRepository;

@Autowired
 private AttemptRepository attemptRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private ActiveSessionsRepository activeSessionsRepository;

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private JwtProvider jwtProvider;

@Autowired
 private  ApplicationEventPublisher eventPublisher;


@PostMapping("forgot-password")
public ResponseEntity<?> forgotPassword(ForgotPasswordForm forgotPasswordForm){
    if (!userRepository.existsByEmail(forgotPasswordForm.getEmail())) {
        throw new BadRequestException("User with given email could not found", ErrorCodes.NO_SUCH_USER);
    } else {
        try {
            eventPublisher.publishEvent(new OnPasswordForgotEvent(forgotPasswordForm.getEmail()));
            SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Email successfuly sent");
            return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
        } catch (Exception re) {
            throw new ErrorWhileSendingEmailException(re.getMessage());
        }
    }
}


@GetMapping("/confirmRegistration")
public ResponseEntity<?> confirmRegistration(String token,HttpServletRequest request){
    if (token == null) {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create("http://localhost:4200")).build();
    }
    if (token != null && jwtProvider.validateJwtToken(token, "verification", request)) {
        String username = jwtProvider.getSubjectFromJwt(token, "verification");
        User user = userRepository.findByEmail(username).orElseThrow(() -> new BadRequestException("User with given email could not found", ErrorCodes.NO_SUCH_USER));
        user.setEnabled(true);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create("http://localhost:4200/mailsuccess")).build();
    } else {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create("http://localhost:4200/mailerror")).build();
    }
}


@PostMapping("signin")
public ResponseEntity<?> authenticateUser(LoginForm loginForm,HttpServletRequest request){
    if (loginForm.getPassword() == null || loginForm.getUsername() == null) {
        throw new BadRequestException("Username and password should be provided", ErrorCodes.USERNAME_AND_PASSWORD);
    }
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
    User user = userRepository.findByUsername(loginForm.getUsername()).orElseThrow(() -> new BadRequestException("User with given username could not found", ErrorCodes.NO_SUCH_USER));
    if (!user.isEnabled()) {
        throw new AccountNotActivatedException("Account has not been activated.");
    }
    UserDetailImpl userPrincipal = UserDetailImpl.build(user);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String accessToken = jwtProvider.generateJwtToken(userPrincipal);
    String refreshToken = jwtProvider.generateRefreshToken(authentication, loginForm.isRememberMe());
    UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
    if (attemptRepository.existsByIp(request.getRemoteAddr())) {
        Attempt attempt = attemptRepository.findById(request.getRemoteAddr()).get();
        attempt.setAttemptCounter(0);
        attemptRepository.save(attempt);
    }
    String userAgent = request.getHeader("User-Agent") == null ? "Not known" : request.getHeader("User-Agent");
    ActiveSessions activeSession = new ActiveSessions(refreshToken, accessToken, userAgent, LocalDateTime.ofInstant(jwtProvider.getExpiredDateFromJwt(refreshToken, "refresh").toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(jwtProvider.getIssueDateFromJwt(refreshToken, "refresh").toInstant(), ZoneId.systemDefault()));
    activeSession.setUser(user);
    activeSessionsRepository.save(activeSession);
    user.addActiveSession(activeSession);
    userRepository.save(user);
    return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getName(), userDetails.getLastName()));
}


@PostMapping("sign-up")
public ResponseEntity<?> registerUser(SignUpForm signUpForm){
    if (userRepository.existsByUsername(signUpForm.getUsername())) {
        throw new BadRequestException("Username is already taken!", ErrorCodes.USERNAME_ALREADY_TAKEN);
    }
    if (userRepository.existsByEmail(signUpForm.getEmail())) {
        throw new BadRequestException("Email is already in use!", ErrorCodes.EMAIL_ALREADY_TAKEN);
    }
    if (!PasswordUtil.isValidPassword(signUpForm.getPassword())) {
        throw new BadRequestException("Password is not valid", ErrorCodes.PASSWORD_NOT_VALID);
    }
    User user = new User(signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByRole(Roles.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    try {
        eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, "ecommerce/authenticator"));
    } catch (Exception re) {
        throw new ErrorWhileSendingEmailException(re.getMessage());
    }
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Hesabınız oluşturuldu. Giriş yapabilmek için mail kutunuzu kontrol edip hesabınızı doğrulayınız.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}