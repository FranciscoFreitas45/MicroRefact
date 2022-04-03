package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class RiverCollaborator {

 private  Long id;

 private  Account account;

 private  River river;

 private  boolean active;

 private  boolean readOnly;

 private  Date dateAdded;

 private long idWINU;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public River getRiver(){
    return river;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
    return account;
}


public Long getId(){
    return id;
}


public boolean isReadOnly(){
    return readOnly;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isReadOnly"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isActive(){
    return active;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isActive"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setActive(boolean active){
    this.active = active;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setActive"))

.queryParam("active",active)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReadOnly(boolean readOnly){
    this.readOnly = readOnly;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReadOnly"))

.queryParam("readOnly",readOnly)
;
restTemplate.put(builder.toUriString(),null);
}


}