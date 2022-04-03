package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "river_rules")
public class Rule {

@Id
@GeneratedValue
 private  long id;

@Transient
 private  River river;

@Column(name = "rule_name")
 private  String name;

@Column(name = "rule_type")
 private  int type;

@Column(name = "rule_conditions")
 private  String conditions;

@Column(name = "rule_actions")
 private  String actions;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "rule_date_add")
 private  Date dateAdded;

@Column(name = "rule_active")
 private  boolean active;

@Column(name = "rule_all_conditions")
 private  boolean matchAllConditions;

@Column(name = "idNMZI")
 private Long idNMZI;

@Transient
 private RiverRequest riverrequest = new RiverRequestImpl();;


public void setName(String name){
    this.name = name;
}


public void setMatchAllConditions(boolean matchAllConditions){
    this.matchAllConditions = matchAllConditions;
}


public River getRiver(){
  this.river = riverrequest.getRiver(this.idNMZI);
return this.river;
}}



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
this.idNMZI = river.getRiver() ;
riverrequest.setRiver(river,this.idNMZI);
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