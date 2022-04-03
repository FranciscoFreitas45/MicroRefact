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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.DropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.DropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.BucketRequest;
import com.ushahidi.swiftriver.core.Request.Impl.BucketRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Bucket;
public class BucketDrop {

 private  long id;

 private  Drop drop;

 private  Bucket bucket;

 private  Date dateAdded;

 private  long veracity;

 private  List<BucketDropLink> links;

 private  List<BucketDropPlace> places;

 private  List<BucketDropTag> tags;

 private  List<BucketDropComment> comments;

 private  List<BucketDropForm> forms;

 private long idINT8;

 private long idFGI4;


public List<BucketDropLink> getLinks(){
    return links;
}


public long getId(){
    return id;
}


public List<BucketDropTag> getTags(){
    return tags;
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


public List<BucketDropComment> getComments(){
    return comments;
}


public Drop getDrop(){
  this.drop = droprequest.getDrop(this.idINT8);
return this.drop;
}}



}