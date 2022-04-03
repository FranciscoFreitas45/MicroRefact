package com.github.haseoo.courier.security;
 import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.exceptions.ResourceException;
import com.github.haseoo.courier.repositories.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.github.haseoo.courier.enums.UserType.ADMIN;
import com.github.haseoo.courier.exceptions.ExceptionMessages.USER_NOT_FOUND_BY_NAME_STRING_FORMAT;
import com.github.haseoo.courier.exceptions.ExceptionMessages.USER_NOT_FOUND_STRING_FORMAT;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

 private  UserRepository userRepository;


public void verifyEditResource(Long id){
    if (currentUser().getUserType() != ADMIN && !currentUser().getId().equals(id)) {
        throw new ResourceException();
    }
}


public UserDetailsImpl currentUser(){
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
        return null;
    }
    return (UserDetailsImpl) authentication.getPrincipal();
}


@Override
public UserDetails loadUserByUsername(String username){
    return UserDetailsImpl.of(userRepository.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_NAME_STRING_FORMAT, username))));
}


public UserDetails loadUserById(Long id){
    return UserDetailsImpl.of(userRepository.getById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_STRING_FORMAT, id))));
}


public boolean isCurrentUserAdmin(){
    return currentUser().getUserType().equals(UserType.ADMIN);
}


}