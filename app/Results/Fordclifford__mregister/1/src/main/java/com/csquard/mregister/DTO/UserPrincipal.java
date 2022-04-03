package com.csquard.mregister.DTO;
 import com.csquard.mregister.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class UserPrincipal implements UserDetails{

 private  Long id;

 private  String name;

 private  String username;

 private  String email;

 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;

public UserPrincipal(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
}
public String getName(){
    return name;
}


public Long getId(){
    return id;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return authorities;
}


@Override
public String getUsername(){
    return username;
}


@Override
public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


}