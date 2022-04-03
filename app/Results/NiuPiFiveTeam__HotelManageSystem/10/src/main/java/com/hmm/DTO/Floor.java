package com.hmm.DTO;
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
public class Floor {

 private  Long floorId;

 private  String floorName;

 private  FloorType floorType;

 private  List<Room> childNodes;


@Id
public Long getFloorId(){
    return floorId;
}


public String getFloorName(){
    return floorName;
}


public FloorType getFloorType(){
    return floorType;
}


@Transient
public List<Room> getChildNodes(){
  this.childNodes = roomrequest.getChildNodes(this.floorId);
return this.childNodes;
}}



}