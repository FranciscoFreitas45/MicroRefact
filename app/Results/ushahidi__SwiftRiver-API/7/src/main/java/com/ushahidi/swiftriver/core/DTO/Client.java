package com.ushahidi.swiftriver.core.DTO;
 import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class Client {

 private  long id;

 private  String clientId;

 private  String clientSecret;

 private  String redirectUri;

 private  String name;

 private  String description;

 private  String homepage;

 private  Boolean active;

 private  Set<Role> roles;

 private  Account account;

 private long id159A;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getClientId(){
    return clientId;
}


public String getName(){
    return name;
}


public String getClientSecret(){
    return clientSecret;
}


public String getHomepage(){
    return homepage;
}


public long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.id159A);
return this.account;
}}



public Boolean getActive(){
    return active;
}


public String getRedirectUri(){
    return redirectUri;
}


public Set<Role> getRoles(){
    return roles;
}


public void setAccount(Account account){
this.id159A = account.getAccount() ;
accountrequest.setAccount(account,this.id159A);
 this.account = account;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAccount"))

.queryParam("account",account)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoles"))

.queryParam("roles",roles)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActive(Boolean active){
    this.active = active;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setActive"))

.queryParam("active",active)
;
restTemplate.put(builder.toUriString(),null);
}


public void setClientSecret(String clientSecret){
    this.clientSecret = clientSecret;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setClientSecret"))

.queryParam("clientSecret",clientSecret)
;
restTemplate.put(builder.toUriString(),null);
}


public void setClientId(String clientId){
    this.clientId = clientId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setClientId"))

.queryParam("clientId",clientId)
;
restTemplate.put(builder.toUriString(),null);
}


}