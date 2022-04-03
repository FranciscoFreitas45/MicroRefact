package upce.semprace.eshop.DTO;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import upce.semprace.eshop.entity.Uzivatel;
public class UserPrinciple implements UserDetails{

 private  long serialVersionUID;

 private  Long id;

 private  String name;

 private  String username;

 private  String email;

 private  String password;

 private  Collection authorities;

public UserPrinciple(Long id, String name, String username, String email, String password, Collection authorities) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
}
@Override
public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


@Override
public Collection getAuthorities(){
    return authorities;
}


@Override
public String getUsername(){
    return username;
}


}