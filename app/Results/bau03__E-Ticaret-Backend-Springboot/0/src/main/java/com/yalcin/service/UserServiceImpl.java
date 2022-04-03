package com.yalcin.service;
 import com.yalcin.entity.User;
import com.yalcin.enums.ErrorCodes;
import com.yalcin.exception.BadRequestException;
import com.yalcin.repository.UserRepository;
import com.yalcin.security.jwt.JwtProvider;
import com.yalcin.security.services.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.JwtProvider;
@Service
public class UserServiceImpl implements UserService{

@Autowired
 private PasswordEncoder passwordEncoder;

@Autowired
 private UserRepository userRepository;

@Autowired
 private JwtProvider jwtProvider;


@Override
public UserDetailImpl getUserDetails(Authentication authentication){
    return ((UserDetailImpl) authentication.getPrincipal());
}


@Override
public User getUserByEmail(String email){
    User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Fail! -> Cause: User cannot find", ErrorCodes.NO_SUCH_USER));
    return user;
}


@Override
public User setNewPassword(User user,String password){
    user.setPassword(passwordEncoder.encode(password));
    return (userRepository.save(user));
}


@Override
public User editUser(User user,String name,String lastName,String age,String phoneNumber){
    user.setName(name);
    user.setLastname(lastName);
    user.setAge(age);
    user.setPhoneNumber(phoneNumber);
    return (userRepository.save(user));
}


@Override
public User getUserByToken(String token,String matter){
    String email = jwtProvider.getSubjectFromJwt(token, matter);
    User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("No such user", ErrorCodes.NO_SUCH_USER));
    return user;
}


@Override
public User getUserWithAuthentication(Authentication authentication){
    UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User cannot find"));
    return user;
}


@Override
public boolean existsByEmail(String email){
    return userRepository.existsByEmail(email);
}


@Override
public User changeEmail(User user,String email){
    user.setEmail(email);
    user.setEnabled(false);
    return (userRepository.save(user));
}


}