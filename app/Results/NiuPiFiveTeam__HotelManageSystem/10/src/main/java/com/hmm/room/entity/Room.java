package com.hmm.room.entity;
 import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hmm.room.util.RoomState;
import com.hmm.room.util.RoomType;
import com.hmm.Request.FloorRequest;
import com.hmm.Request.Impl.FloorRequestImpl;
import com.hmm.DTO.Floor;
@Entity
@Table(name = "t_room")
public class Room {

 private  Long roomId;

 private  String roomNo;

 private  RoomType type;

 private  RoomState state;

 private  String roomPass;

 private  String price;

 private  Floor floorNode;

@Column(name = "floorIdW7A6")
 private Long floorIdW7A6;

@Transient
 private FloorRequest floorrequest = new FloorRequestImpl();;


public String getRoomPass(){
    return roomPass;
}


@Transient
public Floor getFloorNode(){
  this.floorNode = floorrequest.getFloorNode(this.floorIdW7A6);
return this.floorNode;
}}



public void setFloorNode(Floor floorNode){
this.floorIdW7A6 = floorNode.getFloornode() ;
floorrequest.setFloorNode(floorNode,this.floorIdW7A6);
 this.floorNode = floorNode;
}



@Id
public Long getRoomId(){
    return roomId;
}


public void setType(RoomType type){
    this.type = type;
}


public void setPrice(String price){
    this.price = price;
}


public String getPrice(){
    return price;
}


public void setRoomPass(String roomPass){
    this.roomPass = roomPass;
}


public RoomState getState(){
    return state;
}


public RoomType getType(){
    return type;
}


public void setState(RoomState state){
    this.state = state;
}


@Override
public String toString(){
    return "Room [roomId=" + roomId + ", roomNo=" + roomNo + ", type=" + type + ", state=" + state + ", roomPass=" + roomPass + ", price=" + price + "]";
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


public String getRoomNo(){
    return roomNo;
}


public void setRoomId(Long roomId){
    this.roomId = roomId;
}


}