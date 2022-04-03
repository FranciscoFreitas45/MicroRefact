package edu.xr.campusweibo.DTO;
 import edu.xr.campusweibo.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.time.ZonedDateTime;
import edu.xr.campusweibo.Request.PersistentTokenRequest;
import edu.xr.campusweibo.Request.Impl.PersistentTokenRequestImpl;
import edu.xr.campusweibo.DTO.PersistentToken;
public class User extends AbstractAuditingEntityimplements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String login;

 private  String password;

 private  String firstName;

 private  String lastName;

 private  String email;

 private  boolean activated;

 private  String langKey;

 private  String imageUrl;

 private  String activationKey;

 private  String resetKey;

 private  ZonedDateTime resetDate;

 private  Set<Authority> authorities;

 private  Set<PersistentToken> persistentTokens;


public boolean getActivated(){
    return activated;
}


public Long getId(){
    return id;
}


public Set<Authority> getAuthorities(){
    return authorities;
}


public String getImageUrl(){
    return imageUrl;
}


public String getActivationKey(){
    return activationKey;
}


public ZonedDateTime getResetDate(){
    return resetDate;
}


public String getLangKey(){
    return langKey;
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


public Set<PersistentToken> getPersistentTokens(){
  this.persistentTokens = persistenttokenrequest.getPersistentTokens(this.id);
return this.persistentTokens;
}


public String getEmail(){
    return email;
}


public String getFirstName(){
    return firstName;
}


public String getResetKey(){
    return resetKey;
}


}