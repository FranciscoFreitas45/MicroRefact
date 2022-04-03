package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import domain.DomainEntity;
public class UserAccount extends DomainEntityimplements UserDetails{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  Collection<Authority> authorities;

 private  boolean isNotLocked;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public UserAccount() {
    super();
    this.authorities = new ArrayList<Authority>();
}
@NotEmpty
@Valid
@ElementCollection
@Override
public Collection<Authority> getAuthorities(){
    // WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
    return this.authorities;
}


@Size(min = 5, max = 32)
@Column(unique = true)
@Override
public String getUsername(){
    return this.username;
}


@Size(min = 5, max = 32)
@Override
public String getPassword(){
    return this.password;
}


public boolean getIsNotLocked(){
    return this.isNotLocked;
}


public void setAuthorities(Collection<Authority> authorities){
    this.authorities = authorities;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAuthorities"))

.queryParam("authorities",authorities)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsNotLocked(boolean isNotLocked){
    this.isNotLocked = isNotLocked;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsNotLocked"))

.queryParam("isNotLocked",isNotLocked)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


}