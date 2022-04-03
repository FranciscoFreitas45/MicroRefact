package com.yalcin.service;
 import com.yalcin.dto.request.LoginForm;
import com.yalcin.dto.request.SignUpForm;
import com.yalcin.dto.response.LoginResponse;
import com.yalcin.entity;
import com.yalcin.enums.ErrorCodes;
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
import com.yalcin.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.RoleRepository;
@Service
public class AuthenticatorService {

@Autowired
 private UserRepository userRepository;

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private  ApplicationEventPublisher eventPublisher;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private AuthenticatorService authenticatorService;

@Autowired
 private AuthenticationManager authenticationManager;

@Autowired
 private AttemptRepository attemptRepository;

@Autowired
 private ActiveSessionsRepository activeSessionsRepository;

@Autowired
 private JwtProvider jwtProvider;


}