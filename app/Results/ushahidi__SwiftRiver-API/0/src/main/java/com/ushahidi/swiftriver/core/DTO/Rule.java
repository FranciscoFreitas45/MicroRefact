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


public void setName(String name){
    this.name = name;
}


public void setMatchAllConditions(boolean matchAllConditions){
    this.matchAllConditions = matchAllConditions;
}


public River getRiver(){
    return river;
}


public String getName(){
    return name;
}


public boolean isMatchAllConditions(){
    return matchAllConditions;
}


public void setActions(String actions){
    this.actions = actions;
}


public void setRiver(River river){
    this.river = river;
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


public void setType(int type){
    this.type = type;
}


public boolean isActive(){
    return active;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setConditions(String conditions){
    this.conditions = conditions;
}


public void setActive(boolean active){
    this.active = active;
}


public int getType(){
    return type;
}


public Date getDateAdded(){
    return dateAdded;
}


public void setId(long id){
    this.id = id;
}


}