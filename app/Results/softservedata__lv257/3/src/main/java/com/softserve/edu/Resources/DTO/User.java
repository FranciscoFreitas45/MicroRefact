package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import org.jboss.aerogear.security.otp.api.Base32;
import javax.persistence;
import java.util.Collection;
public class User {

 private  Long id;

 private  String username;

 private  String password;

 private  boolean enabled;

 private  String secret;

 private  Collection<Role> roles;

 private  UserDetails userDetails;

 private  VerificationToken verificationToken;

 private  Collection<ResourceRequest> requestsByAdmin;

 private  Collection<ResourceRequest> requestsByRegister;

public User() {
    this.secret = Base32.random();
    this.enabled = false;
}
public VerificationToken getVerificationToken(){
    return verificationToken;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public UserDetails getUserDetails(){
    return userDetails;
}


public String getPassword(){
    return password;
}


public String getSecret(){
    return secret;
}


public Collection<Role> getRoles(){
    return roles;
}


}