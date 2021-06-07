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
@AllArgsConstructor
@Getter
public class UserPrinciple implements UserDetails{

 private  long serialVersionUID;

 private  Long id;

 private  String username;

@JsonIgnore
 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;


@Override
public String getPassword(){
    return password;
}


public UserPrinciple build(User user){
    List<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    return new UserPrinciple(user.getUserId(), user.getMail(), user.getPassword(), grantedAuthorities);
}


@Override
public boolean isAccountNonExpired(){
    return true;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Override
public boolean isEnabled(){
    return true;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null)
        return false;
    if (getClass() != o.getClass())
        return false;
    UserPrinciple user = (UserPrinciple) o;
    return Objects.equals(id, user.id);
}


@Override
public boolean isAccountNonLocked(){
    return true;
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