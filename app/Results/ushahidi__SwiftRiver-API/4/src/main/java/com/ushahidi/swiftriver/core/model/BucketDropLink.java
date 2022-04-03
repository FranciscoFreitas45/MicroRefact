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
@Table(name = "bucket_droplet_links")
public class BucketDropLink {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "buckets_droplets_id")
 private  BucketDrop bucketDrop;

@Transient
 private  Link link;

 private  boolean deleted;

@Column(name = "idOZP4")
 private long idOZP4;

@Transient
 private LinkRequest linkrequest = new LinkRequestImpl();;


public void setLink(Link link){
this.idOZP4 = link.getLink() ;
linkrequest.setLink(link,this.idOZP4);
 this.link = link;
}



public Link getLink(){
  this.link = linkrequest.getLink(this.idOZP4);
return this.link;
}}



public boolean isDeleted(){
    return deleted;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public BucketDrop getBucketDrop(){
    return bucketDrop;
}


public void setBucketDrop(BucketDrop bucketDrop){
    this.bucketDrop = bucketDrop;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}