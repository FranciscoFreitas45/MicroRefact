package com.hmm.DTO;
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
public class Room {

 private  Long roomId;

 private  String roomNo;

 private  RoomType type;

 private  RoomState state;

 private  String roomPass;

 private  String price;

 private  Floor floorNode;


public String getRoomPass(){
    return roomPass;
}


@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
public Floor getFloorNode(){
    return floorNode;
}


@Id
public Long getRoomId(){
    return roomId;
}


public String getPrice(){
    return price;
}


public RoomState getState(){
    return state;
}


public RoomType getType(){
    return type;
}


public String getRoomNo(){
    return roomNo;
}


}