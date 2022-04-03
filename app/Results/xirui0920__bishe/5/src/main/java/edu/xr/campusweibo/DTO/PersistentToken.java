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

 private Long id;


public LocalDate getTokenDate(){
    return tokenDate;
}


public String getIpAddress(){
    return ipAddress;
}


public User getUser(){
    return user;
}


public void setUserAgent(String userAgent){
    if (userAgent.length() >= MAX_USER_AGENT_LEN) {
        this.userAgent = userAgent.substring(0, MAX_USER_AGENT_LEN - 1);
    } else {
        this.userAgent = userAgent;
    }
}


public String getTokenValue(){
    return tokenValue;
}


public void setTokenDate(LocalDate tokenDate){
    this.tokenDate = tokenDate;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setTokenValue(String tokenValue){
    this.tokenValue = tokenValue;
}


public String getSeries(){
    return series;
}


@Override
public int hashCode(){
    return series.hashCode();
}


public void setSeries(String series){
    this.series = series;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    PersistentToken that = (PersistentToken) o;
    if (!series.equals(that.series)) {
        return false;
    }
    return true;
}


public String getUserAgent(){
    return userAgent;
}


@Override
public String toString(){
    return "PersistentToken{" + "series='" + series + '\'' + ", tokenValue='" + tokenValue + '\'' + ", tokenDate=" + tokenDate + ", ipAddress='" + ipAddress + '\'' + ", userAgent='" + userAgent + '\'' + "}";
}


public void setUser(User user){
    this.user = user;
}


}