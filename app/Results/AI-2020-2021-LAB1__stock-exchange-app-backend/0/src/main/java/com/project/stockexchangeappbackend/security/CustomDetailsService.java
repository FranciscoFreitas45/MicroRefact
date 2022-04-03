package com.project.stockexchangeappbackend.security;
 import com.project.stockexchangeappbackend.exception.UserBannedException;
import com.project.stockexchangeappbackend.repository.UserRepository;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
@AllArgsConstructor
public class CustomDetailsService implements UserDetailsService{

 private  UserRepository userRepository;


@Override
@Transactional(readOnly = true)
@LogicBusinessMeasureTime
public UserDetails loadUserByUsername(String username){
    com.project.stockexchangeappbackend.entity.User user = userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
    if (!user.getIsActive()) {
        throw new UserBannedException("User is banned");
    }
    return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
}


}