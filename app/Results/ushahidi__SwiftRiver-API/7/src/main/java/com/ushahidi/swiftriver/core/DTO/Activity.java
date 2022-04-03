package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class Activity {

 private  long id;

 private  Account account;

 private  String action;

 private  String actionOn;

 private  Account actionTo;

 private  Date actionDateAdd;

 private  Boolean confirmed;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public Date getActionDateAdd(){
    return actionDateAdd;
}


public Account getActionTo(){
    return actionTo;
}


public String getActionOn(){
    return actionOn;
}


public long getId(){
    return id;
}


public String getAction(){
    return action;
}


public Boolean getConfirmed(){
    return confirmed;
}


public Account getAccount(){
    return account;
}


public void setAccount(Account account){
    this.account = account;
}


public void setActionDateAdd(Date actionDateAdd){
    this.actionDateAdd = actionDateAdd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setActionDateAdd"))

.queryParam("actionDateAdd",actionDateAdd)
;
restTemplate.put(builder.toUriString(),null);
}


}