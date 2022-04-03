package es.us.isa.ideas.app.security;
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
@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntityimplements UserDetails,Cloneable{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  Collection<Authority> authorities;

public UserAccount() {
    super();
    this.authorities = new ArrayList<Authority>();
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
    return true;
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


public void removeAuthority(Authority authority){
    Assert.notNull(authority);
    Assert.isTrue(authorities.contains(authority));
    authorities.remove(authority);
}


@NotBlank
@Size(min = 5, max = 32)
@Override
public String getPassword(){
    return password;
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


@Override
public int hashCode(){
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
}


@Transient
@Override
public boolean isEnabled(){
    return true;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (!super.equals(obj))
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserAccount other = (UserAccount) obj;
    if (username == null) {
        if (other.username != null)
            return false;
    } else if (!username.equals(other.username))
        return false;
    return true;
}


@Override
public UserAccount clone(){
    UserAccount ua = (UserAccount) super.clone();
    ua.setId(0);
    return ua;
}


public void addAuthority(Authority authority){
    Assert.notNull(authority);
    Assert.isTrue(!authorities.contains(authority));
    authorities.add(authority);
}


public boolean hasAuthority(Authority authority){
    return authorities.contains(authority);
}


}