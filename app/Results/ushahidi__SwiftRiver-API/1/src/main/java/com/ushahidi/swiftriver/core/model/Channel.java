package com.ushahidi.swiftriver.core.model;
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
import com.ushahidi.swiftriver.core.Request.RiverRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverRequestImpl;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverDropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverDropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
@Entity
@Table(name = "river_channels")
public class Channel {

@Id
@GeneratedValue
 private  long id;

@Transient
 private  River river;

@Column(name = "channel")
 private  String channel;

 private  Boolean active;

@Column(name = "date_added")
 private  Date dateAdded;

@Column(name = "date_modified")
 private  Date dateModified;

 private  String parameters;

@Column(name = "drop_count")
 private  int dropCount;

@Transient
 private  List<RiverDrop> drops;

@Column(name = "idATXO")
 private Long idATXO;

@Transient
 private RiverRequest riverrequest = new RiverRequestImpl();;

@Transient
 private RiverDropRequest riverdroprequest = new RiverDropRequestImpl();;

public Channel() {
}
public void setDateModified(Date dateModified){
    this.dateModified = dateModified;
}


public void setDrops(List<RiverDrop> drops){
riverdroprequest.setDrops(drops,this.id);
 this.drops = drops;
}



public River getRiver(){
  this.river = riverrequest.getRiver(this.idATXO);
return this.river;
}}



public void setDropCount(int dropCount){
    this.dropCount = dropCount;
}


public void setRiver(River river){
this.idATXO = river.getRiver() ;
riverrequest.setRiver(river,this.idATXO);
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
  this.drops = riverdroprequest.getDrops(this.id);
return this.drops;
}}



public Date getDateModified(){
    return dateModified;
}


}