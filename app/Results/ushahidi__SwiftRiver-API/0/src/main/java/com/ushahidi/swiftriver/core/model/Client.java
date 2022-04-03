package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "clients")
public class Client {

@Id
@GeneratedValue
 private  long id;

@Column(name = "client_id")
 private  String clientId;

@Column(name = "client_secret")
 private  String clientSecret;

@Column(name = "redirect_uri")
 private  String redirectUri;

 private  String name;

 private  String description;

 private  String homepage;

 private  Boolean active;

@ManyToMany
@JoinTable(name = "roles_clients", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

@Transient
 private  Account account;

@Column(name = "id159A")
 private long id159A;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public void setClientSecret(String clientSecret){
    this.clientSecret = clientSecret;
}


public void setName(String name){
    this.name = name;
}


public void setRedirectUri(String redirectUri){
    this.redirectUri = redirectUri;
}


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


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setActive(Boolean active){
    this.active = active;
}


public void setHomepage(String homepage){
    this.homepage = homepage;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.id159A);
return this.account;
}}



public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.id159A = account.getAccount() ;
accountrequest.setAccount(account,this.id159A);
 this.account = account;
}



public Boolean getActive(){
    return active;
}


public String getRedirectUri(){
    return redirectUri;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public void setClientId(String clientId){
    this.clientId = clientId;
}


public Set<Role> getRoles(){
    return roles;
}


}