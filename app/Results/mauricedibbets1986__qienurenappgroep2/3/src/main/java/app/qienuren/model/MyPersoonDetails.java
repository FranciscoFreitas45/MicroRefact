package app.qienuren.model;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util;
import java.util.stream.Collectors;
public class MyPersoonDetails implements UserDetails{

 private  String userName;

 private  String password;

 private  boolean active;

 private  Collection<? extends GrantedAuthority> authorities;

 private  long id;

public MyPersoonDetails(Persoon persoon) {
    this.userName = persoon.getUserName();
    this.password = persoon.getPassword();
    this.id = persoon.getId();
    this.active = persoon.isActive();
    this.authorities = mapRolesToAuthorities(Arrays.stream(persoon.getRoles().split(",")).collect(Collectors.toList()));
// this.authorities = mapRolesToAuthorities(Collections.singletonList(persoon.getRoles()));
// this.authorities = Arrays.stream(persoon.getRoles().split(","))
// 
// .map(SimpleGrantedAuthority::new)
// .collect(Collectors.toList());
// this.authorities = Collections.singletonList(new GrantedAuthority() {
// @Override
// public String getAuthority() {
// return "USER_ROLE";
// }
// });
}
@Override
public String getPassword(){
    return password;
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
    return active;
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


public Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<String> roles){
    return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return authorities;
}


@Override
public String getUsername(){
    return userName;
}


}