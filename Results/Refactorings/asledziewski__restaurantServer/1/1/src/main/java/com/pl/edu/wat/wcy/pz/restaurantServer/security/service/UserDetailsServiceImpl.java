/*
package com.pl.edu.wat.wcy.pz.restaurantServer.security.service;

import com.DTO.User;
import com.Interface.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserDetailsServiceImpl implements UserDetailsService{

 private UserRepository userRepository;


@Override
public UserDetails loadUserByUsername(String s){
    User user = (User) userRepository.findByMail(s).orElseThrow(() -> new UsernameNotFoundException("User with mail " + s + " not found."));
    return UserPrinciple.build(user);
}


}
*/
