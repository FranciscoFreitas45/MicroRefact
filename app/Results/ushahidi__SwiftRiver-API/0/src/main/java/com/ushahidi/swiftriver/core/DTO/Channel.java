package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Channel {

 private  long id;

 private  River river;

 private  String channel;

 private  Boolean active;

 private  Date dateAdded;

 private  Date dateModified;

 private  String parameters;

 private  int dropCount;

 private  List<RiverDrop> drops;

public Channel() {
}
public void setDateModified(Date dateModified){
    this.dateModified = dateModified;
}


public void setDrops(List<RiverDrop> drops){
    this.drops = drops;
}


public River getRiver(){
    return river;
}


public void setDropCount(int dropCount){
    this.dropCount = dropCount;
}


public void setRiver(River river){
    this.river = river;
}


public long getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public int getDropCount(){
    return dropCount;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setActive(Boolean active){
    this.active = active;
}


public String getChannel(){
    return channel;
}


public Date getDateAdded(){
    return dateAdded;
}


public String getParameters(){
    return parameters;
}


public void setId(long id){
    this.id = id;
}


public Boolean getActive(){
    return active;
}


public List<RiverDrop> getDrops(){
    return drops;
}


public Date getDateModified(){
    return dateModified;
}


}