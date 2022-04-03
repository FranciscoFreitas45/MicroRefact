package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverRequestImpl;
import com.ushahidi.swiftriver.core.DTO.River;
public class Rule {

 private  long id;

 private  River river;

 private  String name;

 private  int type;

 private  String conditions;

 private  String actions;

 private  Date dateAdded;

 private  boolean active;

 private  boolean matchAllConditions;

 private Long idNMZI;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public River getRiver(){
    return river;
}


public String getName(){
    return name;
}


public long getId(){
    return id;
}


public String getConditions(){
    return conditions;
}


public String getActions(){
    return actions;
}


public int getType(){
    return type;
}


public Date getDateAdded(){
    return dateAdded;
}


public void setRiver(River river){
this.idNMZI = river.getRiver() ;
riverrequest.setRiver(river,this.idNMZI);
 this.river = river;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRiver"))

.queryParam("river",river)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateAdded"))

.queryParam("dateAdded",dateAdded)
;
restTemplate.put(builder.toUriString(),null);
}


}