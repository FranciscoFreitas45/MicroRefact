package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.util.GravatarUtil;
import com.ushahidi.swiftriver.core.Request.UserTokenRequest;
import com.ushahidi.swiftriver.core.Request.Impl.UserTokenRequestImpl;
import com.ushahidi.swiftriver.core.DTO.UserToken;
public class User {

 private  long id;

 private  String email;

 private  String name;

 private  String username;

 private  String password;

 private  int logins;

 private  Boolean active;

 private  Boolean expired;

 private  Boolean locked;

 private  Date lastLoginDate;

 private  Date dateCreated;

 private  Set<Role> roles;

 private  Set<UserToken> tokens;

public User() {
}
public String getName(){
    return name;
}


public Date getDateCreated(){
    return dateCreated;
}


public String getAvatar(){
    return GravatarUtil.gravatar(email);
}


public Set<UserToken> getTokens(){
    return tokens;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public int getLogins(){
    return logins;
}


public Set<Role> getRoles(){
    return roles;
}


public Date getLastLoginDate(){
    return lastLoginDate;
}


public Boolean getExpired(){
    return expired;
}


public Boolean getLocked(){
    return locked;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public Boolean getActive(){
    return active;
}


}