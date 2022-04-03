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


}