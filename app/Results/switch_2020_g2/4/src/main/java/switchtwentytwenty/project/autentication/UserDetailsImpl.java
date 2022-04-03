package switchtwentytwenty.project.autentication;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{

 private  long serialVersionUID;

@Getter
 private  Long id;

@Getter
 private  String username;

@Getter
 private  String email;

@Getter
 private  String familyID;

@Getter
 private  Set<RoleJPA> roles;

@Getter
@JsonIgnore
 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;


public UserDetailsImpl build(UserJPA user){
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
    return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getFamilyID(), user.getRoles(), user.getPassword(), authorities);
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
public int hashCode(){
    return Objects.hash(id, username, email, familyID, roles, password, authorities);
}


@Override
public boolean isEnabled(){
    return true;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
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


}