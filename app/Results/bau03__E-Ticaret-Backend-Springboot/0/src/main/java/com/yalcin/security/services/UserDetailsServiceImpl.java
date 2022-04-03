package com.yalcin.security.services;
 import com.yalcin.entity.User;
import com.yalcin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import com.yalcin.Interface.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

@Autowired
 private UserRepository userRepository;

public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}
@Override
@Transactional
public UserDetails loadUserByUsername(String username){
    User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
    return UserDetailImpl.build(user);
}


@Transactional
public UserDetails loadUserByEmail(String email){
    User user = userRepository.findByEmail(email).orElseThrow(() -> new UserPrincipalNotFoundException("User Not Found with -> username or email : " + email));
    return UserDetailImpl.build(user);
}


}