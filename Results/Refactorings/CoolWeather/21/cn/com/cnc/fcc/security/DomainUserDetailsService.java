import cn.com.cnc.fcc.domain.User;
import cn.com.cnc.fcc.repository.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util;
import java.util.stream.Collectors;
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService{

 private  Logger log;

 private  UserRepository userRepository;


public org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin,User user){
    if (!user.getActivated()) {
        throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
    }
    List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
}


@Override
@Transactional
public UserDetails loadUserByUsername(String login){
    log.debug("Authenticating {}", login);
    if (new EmailValidator().isValid(login, null)) {
        return userRepository.findOneWithAuthoritiesByEmail(login).map(user -> createSpringSecurityUser(login, user)).orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
    }
    String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
    return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin).map(user -> createSpringSecurityUser(lowercaseLogin, user)).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
}


}