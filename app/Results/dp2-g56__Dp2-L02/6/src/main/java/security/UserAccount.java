package security;
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
@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
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
public void setIsNotLocked(boolean isNotLocked){
    this.isNotLocked = isNotLocked;
}


public void setPassword(String password){
    this.password = password;
}


public void setUsername(String username){
    this.username = username;
}


@Transient
@Override
public boolean isAccountNonLocked(){
    return this.isNotLocked;
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


public void removeAuthority(Authority authority){
    Assert.notNull(authority);
    Assert.isTrue(this.authorities.contains(authority));
    this.authorities.remove(authority);
}


@Size(min = 5, max = 32)
@Override
public String getPassword(){
    return this.password;
}


public void setAuthorities(Collection<Authority> authorities){
    this.authorities = authorities;
}


@Transient
@Override
public boolean isAccountNonExpired(){
    return true;
}


@Transient
@Override
public boolean isCredentialsNonExpired(){
    return true;
}


public boolean getIsNotLocked(){
    return this.isNotLocked;
}


@Transient
@Override
public boolean isEnabled(){
    return true;
}


public void addAuthority(Authority authority){
    Assert.notNull(authority);
    Assert.isTrue(!this.authorities.contains(authority));
    this.authorities.add(authority);
}


}