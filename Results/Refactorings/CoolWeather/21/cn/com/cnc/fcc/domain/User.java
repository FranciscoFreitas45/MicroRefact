import cn.com.cnc.fcc.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.validation.constraints.Email;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.time.Instant;
@Entity
@Table(name = "jhi_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotNull
@Pattern(regexp = Constants.LOGIN_REGEX)
@Size(min = 1, max = 50)
@Column(length = 50, unique = true, nullable = false)
 private  String login;

@JsonIgnore
@NotNull
@Size(min = 60, max = 60)
@Column(name = "password_hash", length = 60, nullable = false)
 private  String password;

@Size(max = 50)
@Column(name = "first_name", length = 50)
 private  String firstName;

@Size(max = 50)
@Column(name = "last_name", length = 50)
 private  String lastName;

@Email
@Size(min = 5, max = 254)
@Column(length = 254, unique = true)
 private  String email;

@NotNull
@Column(nullable = false)
 private  boolean activated;

@Size(min = 2, max = 6)
@Column(name = "lang_key", length = 6)
 private  String langKey;

@Size(max = 256)
@Column(name = "image_url", length = 256)
 private  String imageUrl;

@Size(max = 20)
@Column(name = "activation_key", length = 20)
@JsonIgnore
 private  String activationKey;

@Size(max = 20)
@Column(name = "reset_key", length = 20)
@JsonIgnore
 private  String resetKey;

@Column(name = "reset_date")
 private  Instant resetDate;

@JsonIgnore
@ManyToMany
@JoinTable(name = "jhi_user_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 20)
 private  Set<Authority> authorities;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setPassword(String password){
    this.password = password;
}


public void setResetDate(Instant resetDate){
    this.resetDate = resetDate;
}


public boolean getActivated(){
    return activated;
}


public Long getId(){
    return id;
}


public Set<Authority> getAuthorities(){
    return authorities;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setAuthorities(Set<Authority> authorities){
    this.authorities = authorities;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getImageUrl(){
    return imageUrl;
}


public String getActivationKey(){
    return activationKey;
}


public void setId(Long id){
    this.id = id;
}


public void setActivationKey(String activationKey){
    this.activationKey = activationKey;
}


public void setActivated(boolean activated){
    this.activated = activated;
}


public Instant getResetDate(){
    return resetDate;
}


public void setResetKey(String resetKey){
    this.resetKey = resetKey;
}


public String getLangKey(){
    return langKey;
}


public void setLangKey(String langKey){
    this.langKey = langKey;
}


public String getLastName(){
    return lastName;
}


public String getLogin(){
    return login;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setLogin(String login){
    this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    User user = (User) o;
    return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated='" + activated + '\'' + ", langKey='" + langKey + '\'' + ", activationKey='" + activationKey + '\'' + "}";
}


public String getFirstName(){
    return firstName;
}


public String getResetKey(){
    return resetKey;
}


}