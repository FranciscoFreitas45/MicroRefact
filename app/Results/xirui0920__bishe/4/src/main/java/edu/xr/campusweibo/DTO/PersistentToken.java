package edu.xr.campusweibo.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
public class PersistentToken implements Serializable{

 private  long serialVersionUID;

 private  int MAX_USER_AGENT_LEN;

 private  String series;

 private  String tokenValue;

 private  LocalDate tokenDate;

 private  String ipAddress;

 private  String userAgent;

 private  User user;


public LocalDate getTokenDate(){
    return tokenDate;
}


public String getIpAddress(){
    return ipAddress;
}


public User getUser(){
    return user;
}


public String getTokenValue(){
    return tokenValue;
}


public String getSeries(){
    return series;
}


public String getUserAgent(){
    return userAgent;
}


}