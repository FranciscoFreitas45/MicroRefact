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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.DropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.DropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.BucketRequest;
import com.ushahidi.swiftriver.core.Request.Impl.BucketRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Bucket;
@Entity
@Table(name = "buckets_droplets")
public class BucketDrop {

@Id
@GeneratedValue
 private  long id;

@Transient
 private  Drop drop;

@Transient
 private  Bucket bucket;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "droplet_date_added")
 private  Date dateAdded;

@Column(name = "droplet_veracity")
 private  long veracity;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "bucketDrop")
 private  List<BucketDropLink> links;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "bucketDrop")
 private  List<BucketDropPlace> places;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "bucketDrop")
 private  List<BucketDropTag> tags;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "bucketDrop")
 private  List<BucketDropComment> comments;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "drop")
 private  List<BucketDropForm> forms;

@Column(name = "idINT8")
 private long idINT8;

@Transient
 private DropRequest droprequest = new DropRequestImpl();;

@Column(name = "idFGI4")
 private long idFGI4;

@Transient
 private BucketRequest bucketrequest = new BucketRequestImpl();;


public List<BucketDropLink> getLinks(){
    return links;
}


public void setPlaces(List<BucketDropPlace> places){
    this.places = places;
}


public long getId(){
    return id;
}


public void setBucket(Bucket bucket){
this.idFGI4 = bucket.getBucket() ;
bucketrequest.setBucket(bucket,this.idFGI4);
 this.bucket = bucket;
}



public List<BucketDropTag> getTags(){
    return tags;
}


public void setVeracity(long veracity){
    this.veracity = veracity;
}


public void setTags(List<BucketDropTag> tags){
    this.tags = tags;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setForms(List<BucketDropForm> forms){
    this.forms = forms;
}


public Bucket getBucket(){
  this.bucket = bucketrequest.getBucket(this.idFGI4);
return this.bucket;
}}



public Date getDateAdded(){
    return dateAdded;
}


public List<BucketDropForm> getForms(){
    return forms;
}


public long getVeracity(){
    return veracity;
}


public List<BucketDropPlace> getPlaces(){
    return places;
}


public void setDrop(Drop drop){
this.idINT8 = drop.getDrop() ;
droprequest.setDrop(drop,this.idINT8);
 this.drop = drop;
}



public void setLinks(List<BucketDropLink> links){
    this.links = links;
}


public List<BucketDropComment> getComments(){
    return comments;
}


public void setId(long id){
    this.id = id;
}


public Drop getDrop(){
  this.drop = droprequest.getDrop(this.idINT8);
return this.drop;
}}



public void setComments(List<BucketDropComment> comments){
    this.comments = comments;
}


}