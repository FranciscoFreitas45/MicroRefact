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
import com.ushahidi.swiftriver.core.Request.RiverRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverRequestImpl;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverDropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverDropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
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

 private Long idATXO;

public Channel() {
}
public River getRiver(){
  this.river = riverrequest.getRiver(this.idATXO);
return this.river;
}}



public long getId(){
    return id;
}


public int getDropCount(){
    return dropCount;
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