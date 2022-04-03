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
    return bucket;
}


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
    return drop;
}


public void setComments(List<BucketDropComment> comments){
    this.comments = comments;
}


}