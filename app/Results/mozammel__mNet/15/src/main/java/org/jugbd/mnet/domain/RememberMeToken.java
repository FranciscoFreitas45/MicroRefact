package org.jugbd.mnet.domain;
 import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class RememberMeToken {

@Id
 private  String series;

 private  String username;

 private  String token;

 private  Date lastUsedDate;

public RememberMeToken(PersistentRememberMeToken rememberMeToken) {
    this.series = rememberMeToken.getSeries();
    this.username = rememberMeToken.getUsername();
    this.token = rememberMeToken.getTokenValue();
    this.lastUsedDate = rememberMeToken.getDate();
}public RememberMeToken() {
}
public Date getLastUsedDate(){
    return lastUsedDate;
}


public void setLastUsedDate(Date lastUsedDate){
    this.lastUsedDate = lastUsedDate;
}


public String getSeries(){
    return series;
}


public void setUsername(String username){
    this.username = username;
}


public void setToken(String token){
    this.token = token;
}


public void setSeries(String series){
    this.series = series;
}


public String getToken(){
    return token;
}


public String getUsername(){
    return username;
}


}