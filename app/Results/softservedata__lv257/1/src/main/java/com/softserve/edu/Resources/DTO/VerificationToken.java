package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Calendar;
import java.util.Date;
import com.softserve.edu.Resources.Request.UserRequest;
import com.softserve.edu.Resources.Request.Impl.UserRequestImpl;
import com.softserve.edu.Resources.DTO.User;
public class VerificationToken {

 private  int EXPIRATION;

 private  Long id;

 private  String token;

 private  User user;

 private  Date expiryDate;

 private Long idXLHJ;

public VerificationToken() {
    super();
}public VerificationToken(final String token) {
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
}public VerificationToken(final String token, final User user) {
    this.token = token;
    this.user = user;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
}
public Date getExpiryDate(){
    return expiryDate;
}


public User getUser(){
    return user;
}


public Long getId(){
    return id;
}


public String getToken(){
    return token;
}


}