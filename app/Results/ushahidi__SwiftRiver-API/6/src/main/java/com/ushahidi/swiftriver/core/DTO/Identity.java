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
  this.drops = droprequest.getDrops(this.id);
return this.drops;
}}



public String getOriginId(){
    return originId;
}


public Date getDateModified(){
    return dateModified;
}


}