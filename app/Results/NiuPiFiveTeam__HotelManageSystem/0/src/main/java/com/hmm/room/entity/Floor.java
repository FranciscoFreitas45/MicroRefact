package com.hmm.room.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.hmm.room.util.FloorType;
import com.hmm.Request.RoomRequest;
import com.hmm.Request.Impl.RoomRequestImpl;
import com.hmm.DTO.Room;
@Entity
@Table(name = "t_floor")
public class Floor {

 private  Long floorId;

 private  String floorName;

 private  FloorType floorType;

 private  List<Room> childNodes;

@Transient
 private RoomRequest roomrequest = new RoomRequestImpl();;


public void setFloorId(Long floorId){
    this.floorId = floorId;
}


public void setFloorType(FloorType floorType){
    this.floorType = floorType;
}


@Id
public Long getFloorId(){
    return floorId;
}


public String getFloorName(){
    return floorName;
}


public void setFloorName(String floorName){
    this.floorName = floorName;
}


@Override
public String toString(){
    return "Floor [floorId=" + floorId + ", floorName=" + floorName + ", floorType=" + floorType + "]";
}


public FloorType getFloorType(){
    return floorType;
}


public void setChildNodes(List<Room> childNodes){
roomrequest.setChildNodes(childNodes,this.floorId);
 this.childNodes = childNodes;
}



@Transient
public List<Room> getChildNodes(){
  this.childNodes = roomrequest.getChildNodes(this.floorId);
return this.childNodes;
}}



}