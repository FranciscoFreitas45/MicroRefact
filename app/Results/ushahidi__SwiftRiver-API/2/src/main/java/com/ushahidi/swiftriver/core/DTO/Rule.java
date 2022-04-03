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


public River getRiver(){
  this.river = riverrequest.getRiver(this.idNMZI);
return this.river;
}}



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


}