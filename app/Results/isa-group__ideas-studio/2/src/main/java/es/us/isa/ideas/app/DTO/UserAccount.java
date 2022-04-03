package es.us.isa.ideas.app.DTO;
 import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import es.us.isa.ideas.app.entities.DomainEntity;
public class UserAccount extends DomainEntityimplements UserDetails,Cloneable{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  Collection<Authority> authorities;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public UserAccount() {
    super();
    this.authorities = new ArrayList<Authority>();
}
@NotEmpty
@Valid
@ElementCollection
@Cascade(value = { CascadeType.ALL })
@Override
public Collection<Authority> getAuthorities(){
    // WARNING: Should return an unmodifiable copy, but it's not possible
    // with hibernate!
    return authorities;
}


@NotBlank
@Size(min = 1, max = 32)
@Column(unique = true)
@Override
public String getUsername(){
    return username;
}


@NotBlank
@Size(min = 5, max = 32)
@Override
public String getPassword(){
    return password;
}


public void setAuthorities(Collection<Authority> authorities){
    this.authorities = authorities;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAuthorities"))

.queryParam("authorities",authorities)
;
restTemplate.put(builder.toUriString(),null);
}


}