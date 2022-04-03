package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "identities")
public class Identity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "Seq")
@TableGenerator(name = "Seq", table = "seq", pkColumnName = "name", valueColumnName = "id", pkColumnValue = "identities")
 private  long id;

@Column(name = "hash")
 private  String hash;

@Column(name = "channel")
 private  String channel;

@Lob
@Column(name = "identity_orig_id")
 private  String originId;

@Column(name = "identity_username")
 private  String username;

@Column(name = "identity_name")
 private  String name;

@Lob
@Column(name = "identity_avatar")
 private  String avatar;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "identity_date_add")
 private  Date dateAdded;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "identity_date_modified")
 private  Date dateModified;

@Transient
 private  List<Drop> drops;

@Transient
 private DropRequest droprequest = new DropRequestImpl();;

public Identity() {
}
public void setName(String name){
    this.name = name;
}


public void setDateModified(Date dateModified){
    this.dateModified = dateModified;
}


public void setDrops(List<Drop> drops){
droprequest.setDrops(drops,this.id);
 this.drops = drops;
}



public String getName(){
    return name;
}


public String getHash(){
    return hash;
}


public void setUsername(String username){
    this.username = username;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public void setOriginId(String originId){
    this.originId = originId;
}


public String getAvatar(){
    return avatar;
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


public String getUsername(){
    return username;
}


public String getChannel(){
    return channel;
}


public Date getDateAdded(){
    return dateAdded;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Identity other = (Identity) obj;
    if (hash == null) {
        if (other.hash != null)
            return false;
    } else if (!hash.equals(other.hash))
        return false;
    return true;
}


public void setId(long id){
    this.id = id;
}


public void setHash(String hash){
    this.hash = hash;
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