package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class Form {

 private  Long id;

 private  Account account;

 private  String name;

 private  Date dateAdded;

 private  Date dateModified;

 private  List<FormField> fields;

 private long idQGFJ;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getName(){
    return name;
}


public List<FormField> getFields(){
    return fields;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idQGFJ);
return this.account;
}}



public Long getId(){
    return id;
}


public Date getDateModified(){
    return dateModified;
}


public void setAccount(Account account){
this.idQGFJ = account.getAccount() ;
accountrequest.setAccount(account,this.idQGFJ);
 this.account = account;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAccount"))

.queryParam("account",account)
;
restTemplate.put(builder.toUriString(),null);
}


}