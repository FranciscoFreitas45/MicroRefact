package com.ushahidi.swiftriver.core.DTO;
 import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.DropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.DropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Drop;
public class Identity implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String hash;

 private  String channel;

 private  String originId;

 private  String username;

 private  String name;

 private  String avatar;

 private  Date dateAdded;

 private  Date dateModified;

 private  List<Drop> drops;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public Identity() {
}
public String getName(){
    return name;
}


public String getHash(){
    return hash;
}


public String getAvatar(){
    return avatar;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public String getChannel(){
    return channel;
}


public Date getDateAdded(){
    return dateAdded;
}


public List<Drop> getDrops(){
    return drops;
}


public String getOriginId(){
    return originId;
}


public Date getDateModified(){
    return dateModified;
}


public void setId(long id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAvatar(String avatar){
    this.avatar = avatar;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAvatar"))

.queryParam("avatar",avatar)
;
restTemplate.put(builder.toUriString(),null);
}


}