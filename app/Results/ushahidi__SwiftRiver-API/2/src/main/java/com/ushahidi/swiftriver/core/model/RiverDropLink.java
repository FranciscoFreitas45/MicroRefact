package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.LinkRequest;
import com.ushahidi.swiftriver.core.Request.Impl.LinkRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Link;
@Entity
@Table(name = "river_droplet_links")
public class RiverDropLink {

@GeneratedValue
@Id
 private  long id;

@ManyToOne
@JoinColumn(name = "rivers_droplets_id")
 private  RiverDrop riverDrop;

@Transient
 private  Link link;

 private  boolean deleted;

@Column(name = "idM175")
 private long idM175;

@Transient
 private LinkRequest linkrequest = new LinkRequestImpl();;

public RiverDropLink() {
}
public void setLink(Link link){
this.idM175 = link.getLink() ;
linkrequest.setLink(link,this.idM175);
 this.link = link;
}



public Link getLink(){
  this.link = linkrequest.getLink(this.idM175);
return this.link;
}}



public boolean isDeleted(){
    return deleted;
}


public RiverDrop getRiverDrop(){
    return riverDrop;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setRiverDrop(RiverDrop riverDrop){
    this.riverDrop = riverDrop;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}