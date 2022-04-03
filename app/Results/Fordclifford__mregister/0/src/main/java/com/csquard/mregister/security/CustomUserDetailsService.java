package com.csquard.mregister.security;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.csquard.mregister.model.User;
import com.csquard.mregister.repository.UserRepository;
import com.csquard.mregister.Interface.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{

@Autowired
 private UserRepository userRepository;


@Override
@Transactional
public UserDetails loadUserByUsername(String usernameOrEmail){
    // Let people login with either username or email
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
    return UserPrincipal.create(user);
}


@Transactional
public UserDetails loadUserById(Long id){
    User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    return UserPrincipal.create(user);
}


}