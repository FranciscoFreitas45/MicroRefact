package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Designation;
import org.jugbd.mnet.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@NamedQuery(name = "User.findByUsername", query = "from User u where u.username = ?")
@JsonIgnoreProperties({ "createdBy", "lastModifiedBy" })
public class User extends PersistentObjectimplements Auditable,Serializable,UserDetails{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@NotEmpty
@Size(min = 4, max = 100)
 private  String fullName;

@NotNull
@Enumerated(EnumType.STRING)
 private  Designation designation;

@NotEmpty
@Size(min = 4, max = 30)
@Column(nullable = false, unique = true)
@Pattern(regexp = "^[a-z0-9_-]{4,30}$", message = "Username may only contain upper case or lower case character, digit, underscore and hyphen")
 private  String username;

// quick fix, have to sort out later
@NotEmpty(groups = User.class)
@Size(min = 8, max = 32)
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{8,}$", message = "Password must contain at least one special character (@ # $ % ^ & + !), one digit, one lowercase and upper case letter and no whitespace.")
@Transient
@JsonIgnore
 private  String password;

@JsonIgnore
@Size(max = 280)
 private  String hashedPassword;

@JsonIgnore
@Size(max = 16)
 private  String salt;

@Email
 private  String email;

@Pattern(regexp = "^01(1|5|6|7|8|9)\\d{8}$")
 private  String phoneNumber;

@NotEmpty(message = "You must select one role")
@ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
@Enumerated(EnumType.STRING)
@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
 private  List<Role> roles;

 private  boolean accountNonExpired;

 private  boolean accountNonLocked;

 private  boolean credentialsNonExpired;

 private  boolean enabled;

public User() {
}public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
}
public void setPassword(String password){
    this.password = password;
}


public void setAccountNonExpired(boolean accountNonExpired){
    this.accountNonExpired = accountNonExpired;
}


public String getSalt(){
    return salt;
}


public Long getId(){
    return id;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role : this.roles) {
        authorities.add(new SimpleGrantedAuthority(role.name()));
    }
    return authorities;
}


@Override
public String getUsername(){
    return username;
}


@Override
public boolean isAccountNonExpired(){
    return accountNonExpired;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (version != null ? version.hashCode() : 0);
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (accountNonExpired ? 1 : 0);
    result = 31 * result + (accountNonLocked ? 1 : 0);
    result = 31 * result + (credentialsNonExpired ? 1 : 0);
    result = 31 * result + (enabled ? 1 : 0);
    return result;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setId(Long id){
    this.id = id;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public void setRoles(List<Role> roles){
    this.roles = roles;
}


public String getFullName(){
    return fullName;
}


public List<Role> getRoles(){
    return roles;
}


public void setCredentialsNonExpired(boolean credentialsNonExpired){
    this.credentialsNonExpired = credentialsNonExpired;
}


public Long getVersion(){
    return version;
}


public Designation getDesignation(){
    return designation;
}


public void setUsername(String username){
    this.username = username;
}


public void setVersion(Long version){
    this.version = version;
}


public void setAccountNonLocked(boolean accountNonLocked){
    this.accountNonLocked = accountNonLocked;
}


@Override
public boolean isAccountNonLocked(){
    return accountNonLocked;
}


public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


@Override
public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


@Override
public boolean isCredentialsNonExpired(){
    return credentialsNonExpired;
}


public void setHashedPassword(String hashedPassword){
    this.hashedPassword = hashedPassword;
}


@Override
public boolean isEnabled(){
    return enabled;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    User user = (User) o;
    if (accountNonExpired != user.accountNonExpired)
        return false;
    if (accountNonLocked != user.accountNonLocked)
        return false;
    if (credentialsNonExpired != user.credentialsNonExpired)
        return false;
    if (enabled != user.enabled)
        return false;
    if (id != user.id)
        return false;
    if (roles != null ? !roles.equals(user.roles) : user.roles != null)
        return false;
    if (email != null ? !email.equals(user.email) : user.email != null)
        return false;
    if (!password.equals(user.password))
        return false;
    if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null)
        return false;
    if (!username.equals(user.username))
        return false;
    return true;
}


public String getHashedPassword(){
    return hashedPassword;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User{" + "id=" + id + ", version=" + version + ", fullName='" + fullName + '\'' + ", designation=" + designation + ", username='" + username + '\'' + ", hashedPassword='" + hashedPassword + '\'' + ", salt='" + salt + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", roles=" + roles + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + '}';
}


public void setDesignation(Designation designation){
    this.designation = designation;
}


}