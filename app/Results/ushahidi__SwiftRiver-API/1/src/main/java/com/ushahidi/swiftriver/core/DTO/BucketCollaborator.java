package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class BucketCollaborator {

 private  long id;

 private  Account account;

 private  Bucket bucket;

 private  boolean active;

 private  boolean readOnly;

 private  Date dateAdded;

 private long idXXQN;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public Bucket getBucket(){
    return bucket;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
    return account;
}


public long getId(){
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