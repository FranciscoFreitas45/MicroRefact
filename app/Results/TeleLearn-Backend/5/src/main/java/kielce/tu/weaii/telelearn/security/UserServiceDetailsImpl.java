package kielce.tu.weaii.telelearn.security;
 import kielce.tu.weaii.telelearn.exceptions.users.UserNotFoundException;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.repositories.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceDetailsImpl implements UserDetailsService{

 private  UserRepository userRepository;


public User getCurrentUser(){
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication.getPrincipal().equals("anonymousUser")) {
        return null;
    }
    return (User) authentication.getPrincipal();
}


@Override
public UserDetails loadUserByUsername(String usernameOrEmail){
    return userRepository.getUserByLoginOrEmail(usernameOrEmail).orElseThrow(() -> new UserNotFoundException(usernameOrEmail));
}


public UserDetails loadUserById(Long id){
    return userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
}


}