package com.yalcin.security.services;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yalcin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
public class UserDetailImpl implements OAuth2User,UserDetails{

 private  long serialVersionUID;

 private  Integer id;

 private  String username;

 private  String email;

 private  String name;

 private  String lastName;

 private  String phoneNumber;

 private  String age;

@JsonIgnore
 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;

 private  Map<String,Object> attributes;

public UserDetailImpl(Integer id, String username, String email, String password, String name, String lastName, String phoneNumber, String age, Collection<? extends GrantedAuthority> authorities) {
    this.name = name;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.age = age;
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
}
public void setName(String name){
    this.name = name;
}


public String getAge(){
    return age;
}


public void setPassword(String password){
    this.password = password;
}


@Override
public String getName(){
    return this.name;
}


public Integer getId(){
    return id;
}


public void setAttributes(Map<String,Object> attributes){
    this.attributes = attributes;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return authorities;
}


@Override
public String getUsername(){
    return username;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


@Override
public boolean isAccountNonExpired(){
    return true;
}


public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
    this.authorities = authorities;
}


public void setId(Integer id){
    this.id = id;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public Map<String,Object> getAttributes(){
    return attributes;
}


public void setUsername(String username){
    this.username = username;
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


public String getLastName(){
    return lastName;
}


@Override
public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public UserDetailImpl build(User user,Map<String,Object> attributes){
    UserDetailImpl userPrincipal = UserDetailImpl.build(user);
    userPrincipal.setAttributes(attributes);
    return userPrincipal;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
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
    UserDetailImpl user = (UserDetailImpl) o;
    return Objects.equals(id, user.id);
}


public String getEmail(){
    return email;
}


public void setAge(String age){
    this.age = age;
}


}