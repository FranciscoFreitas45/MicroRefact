package edu.xr.campusweibo.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import edu.xr.campusweibo.Request.UserRequest;
import edu.xr.campusweibo.Request.Impl.UserRequestImpl;
import edu.xr.campusweibo.DTO.User;
@Entity
@Table(name = "jhi_persistent_token")
public class PersistentToken implements Serializable{

 private  long serialVersionUID;

 private  int MAX_USER_AGENT_LEN;

@Id
 private  String series;

@JsonIgnore
@NotNull
@Column(name = "token_value", nullable = false)
 private  String tokenValue;

@Column(name = "token_date")
 private  LocalDate tokenDate;

@Size(min = 0, max = 39)
@Column(name = "ip_address", length = 39)
 private  String ipAddress;

@Column(name = "user_agent")
 private  String userAgent;

@Transient
 private  User user;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


public LocalDate getTokenDate(){
    return tokenDate;
}


public String getIpAddress(){
    return ipAddress;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
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
 userrequest.setUser(user,this.id);
}



}