package pl.szymanski.sharelibrary.security;
 import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
import pl.szymanski.sharelibrary.repositories.ports.UserRepository;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

 private  UserRepository userRepository;


@Override
public UserDetails loadUserByUsername(String s){
    User user = userRepository.getUserByUsernameOrEmail(s, s).orElseThrow(() -> new UsernameNotFoundException((String.format(ExceptionMessages.USER_NOT_FOUND_BY_EMAIL_OR_USERNAME_EXCEPTION_FORMAT, s))));
    return UserDetailsImpl.of(user);
}


public UserDetails loadUserById(Long id){
    User user = userRepository.getUserById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_FORMAT, id)));
    return UserDetailsImpl.of(user);
}


}