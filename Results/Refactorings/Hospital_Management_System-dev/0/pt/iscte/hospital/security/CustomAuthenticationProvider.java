import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pt.iscte.hospital.services.user.UserService;
import java.util.List;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

@Autowired
 private  UserService userService;

@Autowired
 private  PasswordEncoder passwordEncoder;


@Override
public Authentication authenticate(Authentication authentication){
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    if (userService.validateUser(username, password)) {
        // use the credentials
        // and authenticate against the third-party system
        List<GrantedAuthority> roles = userService.getAuthorities(username);
        return new UsernamePasswordAuthenticationToken(username, password, // SimpleGrantedAuthority "ROLE_"
        roles);
    } else {
        throw new BadCredentialsException("Invalid username/password");
    }
}


@Override
public boolean supports(Class<?> authentication){
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
}


}