package com.example.smartkitchenbackend.security;
 import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

 private  UserRepository userRepository;


@Override
@Transactional
public UserDetails loadUserByUsername(String usernameOrEmail){
    // Let people login with either username or email
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    return UserPrincipal.create(user);
}


@Transactional
public UserDetails loadUserById(Long id){
    User user = userRepository.findById(id);
    return UserPrincipal.create(user);
}


}