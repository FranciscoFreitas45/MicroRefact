package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.TagRequest;
import com.ushahidi.swiftriver.core.Request.Impl.TagRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Tag;
@Entity
@Table(name = "river_droplet_tags")
public class RiverDropTag {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "rivers_droplets_id")
 private  RiverDrop riverDrop;

@Transient
 private  Tag tag;

 private  boolean deleted;

@Column(name = "id3OUD")
 private long id3OUD;

@Transient
 private TagRequest tagrequest = new TagRequestImpl();;


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


public void setTag(Tag tag){
this.id3OUD = tag.getTag() ;
tagrequest.setTag(tag,this.id3OUD);
 this.tag = tag;
}



public void setRiverDrop(RiverDrop riverDrop){
    this.riverDrop = riverDrop;
}


public Tag getTag(){
  this.tag = tagrequest.getTag(this.id3OUD);
return this.tag;
}}



public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}