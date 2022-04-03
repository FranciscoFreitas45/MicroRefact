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
@Table(name = "bucket_droplet_tags")
public class BucketDropTag {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "buckets_droplets_id")
 private  BucketDrop bucketDrop;

@Transient
 private  Tag tag;

 private  boolean deleted;

@Column(name = "idQ98E")
 private long idQ98E;

@Transient
 private TagRequest tagrequest = new TagRequestImpl();;


public boolean isDeleted(){
    return deleted;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setTag(Tag tag){
this.idQ98E = tag.getTag() ;
tagrequest.setTag(tag,this.idQ98E);
 this.tag = tag;
}



public Tag getTag(){
  this.tag = tagrequest.getTag(this.idQ98E);
return this.tag;
}}



public BucketDrop getBucketDrop(){
    return bucketDrop;
}


public void setBucketDrop(BucketDrop drop){
    this.bucketDrop = drop;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}