package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.PlaceRequest;
import com.ushahidi.swiftriver.core.Request.Impl.PlaceRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Place;
@Entity
@Table(name = "bucket_droplet_places")
public class BucketDropPlace {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "buckets_droplets_id")
 private  BucketDrop bucketDrop;

@Transient
 private  Place place;

 private  boolean deleted;

@Column(name = "idEIIX")
 private long idEIIX;

@Transient
 private PlaceRequest placerequest = new PlaceRequestImpl();;


public boolean isDeleted(){
    return deleted;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public Place getPlace(){
  this.place = placerequest.getPlace(this.idEIIX);
return this.place;
}}



public void setPlace(Place place){
this.idEIIX = place.getPlace() ;
placerequest.setPlace(place,this.idEIIX);
 this.place = place;
}



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