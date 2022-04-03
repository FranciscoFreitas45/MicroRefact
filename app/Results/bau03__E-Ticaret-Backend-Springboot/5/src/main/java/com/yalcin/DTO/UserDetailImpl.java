package com.yalcin.DTO;
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

 private  String password;

 private  Collection<? extends GrantedAuthority> authorities;

 private  Map<String,Object> attributes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

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
public String getAge(){
    return age;
}


@Override
public String getName(){
    return this.name;
}


public Integer getId(){
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


public String getPhoneNumber(){
    return phoneNumber;
}


public Map<String,Object> getAttributes(){
    return attributes;
}


public String getLastName(){
    return lastName;
}


@Override
public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public UserDetailImpl build(User user,Map<String,Object> attributes){
    UserDetailImpl userPrincipal = UserDetailImpl.build(user);
    userPrincipal.setAttributes(attributes);
    return userPrincipal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))

.queryParam("user",user)
.queryParam("attributes",attributes)
;
UserDetailImpl aux = restTemplate.getForObject(builder.toUriString(),UserDetailImpl.class);
return aux;
}


}