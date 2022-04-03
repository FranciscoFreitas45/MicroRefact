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
@Table(name = "river_droplet_places")
public class RiverDropPlace {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "rivers_droplets_id")
 private  RiverDrop riverDrop;

@Transient
 private  Place place;

 private  boolean deleted;

@Column(name = "id6ANM")
 private long id6ANM;

@Transient
 private PlaceRequest placerequest = new PlaceRequestImpl();;


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


public Place getPlace(){
  this.place = placerequest.getPlace(this.id6ANM);
return this.place;
}}



public void setRiverDrop(RiverDrop riverDrop){
    this.riverDrop = riverDrop;
}


public void setPlace(Place place){
this.id6ANM = place.getPlace() ;
placerequest.setPlace(place,this.id6ANM);
 this.place = place;
}



public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}