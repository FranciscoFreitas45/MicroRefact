package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class UserPrinciple implements UserDetails{

 private  long serialVersionUID;

 private  Long id;

 private  String username;

 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;

private UserPrinciple(Long userId, String mail, String password, List<GrantedAuthority> grantedAuthorities) {
    this.id = userId;
    this.username = mail;
    this.password = password;
    this.authorities = grantedAuthorities;
}
@Override
public String getPassword(){
    return password;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return authorities;
}


@Override
public String getUsername(){
    return username;
}


}