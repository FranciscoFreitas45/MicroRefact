package com.gp.cricket.config.security;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class JwtUserDetails implements UserDetails{

 private  long serialVersionUID;

 private  Long id;

 private  String username;

 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;

public JwtUserDetails(Long id, String username, String password, String role) {
    this.id = id;
    this.username = username;
    this.password = password;
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));
    this.authorities = authorities;
}
@JsonIgnore
@Override
public String getPassword(){
    return password;
}


@JsonIgnore
@Override
public boolean isAccountNonExpired(){
    return true;
}


@JsonIgnore
@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Override
public boolean isEnabled(){
    return true;
}


@JsonIgnore
public Long getId(){
    return id;
}


@JsonIgnore
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