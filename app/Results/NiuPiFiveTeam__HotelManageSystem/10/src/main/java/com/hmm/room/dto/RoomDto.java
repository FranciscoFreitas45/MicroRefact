package com.hmm.room.dto;
 import com.hmm.room.util.RoomState;
import com.hmm.room.util.RoomType;
public class RoomDto {

 private  Long roomId;

 private  String roomNo;

 private  int type;

 private  int state;

 private  String roomPass;

 private  Long floorId;

 private  String price;


public String getRoomPass(){
    return roomPass;
}


public Long getRoomId(){
    return roomId;
}


public void setType(int type){
    this.type = type;
}


public void setPrice(String price){
    this.price = price;
}


public String getPrice(){
    return price;
}


public void setFloorId(Long floorId){
    this.floorId = floorId;
}


public void setRoomPass(String roomPass){
    this.roomPass = roomPass;
}


public int getState(){
    return state;
}


public int getType(){
    return type;
}


public Long getFloorId(){
    return floorId;
}


public void setState(int state){
    this.state = state;
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


public void setRoomId(Long roomId){
    this.roomId = roomId;
}


public String getRoomNo(){
    return roomNo;
}


}