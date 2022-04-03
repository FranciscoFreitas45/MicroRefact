package com.ushahidi.swiftriver.core.api.auth.crowdmapid;
 import java.util.HashSet;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.dao.UserDao;
import com.ushahidi.swiftriver.core.model.Role;
import com.ushahidi.swiftriver.core.model.User;
public class CrowdmapIDAuthenticationProvider implements AuthenticationProvider{

 private  UserDao userDao;

 private  CrowdmapIDClient crowdmapIDClient;


public void setCrowdmapIDClient(CrowdmapIDClient crowdmapIDClient){
    this.crowdmapIDClient = crowdmapIDClient;
}


@Transactional(readOnly = true)
@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException{
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = userDao.findByUsernameOrEmail(username);
    if (user == null || !crowdmapIDClient.signIn(username, password)) {
        throw new BadCredentialsException(String.format("Invalid username/password pair for %s", username));
    }
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    for (Role role : user.getRoles()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
    }
    UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username, authentication.getCredentials(), authorities);
    result.setDetails(authentication.getDetails());
    return result;
}


@Override
public boolean supports(Class<?> authentication){
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
}


public void setUserDao(UserDao userDao){
    this.userDao = userDao;
}


}