package pl.szymanski.sharelibrary.security;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.szymanski.sharelibrary.entity.User;
import java.util.Collection;
import java.util.Collections;
@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{

 private  Long id;

 private  String email;

 private  String password;

 private  String username;

 private  boolean enabled;


@Override
public boolean isAccountNonExpired(){
    return true;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


public UserDetailsImpl of(User user){
    return new UserDetailsImpl(user.getId(), user.getEmail(), ((user.getPassword() != null) ? new String(user.getPassword()) : null), user.getUsername(), true);
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return Collections.singletonList(new SimpleGrantedAuthority("User"));
}


}