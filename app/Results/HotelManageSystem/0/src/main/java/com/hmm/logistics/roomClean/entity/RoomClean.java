package com.hmm.logistics.roomClean.entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.hmm.logistics.roomClean.util.RoomCleanState;
import com.hmm.room.entity.Room;
@Entity
@Table(name = "t_roomclean")
public class RoomClean {

 private  Long roomCleanId;

 private  String roomOther;

 private  RoomCleanState roomCleanState;

 private  Room room;


public RoomCleanState getRoomCleanState(){
    return roomCleanState;
}


public void setRoom(Room room){
    this.room = room;
}


public String getRoomOther(){
    return roomOther;
}


public void setRoomOther(String roomOther){
    this.roomOther = roomOther;
}


@OneToOne
public Room getRoom(){
    return room;
}


public void setRoomCleanState(RoomCleanState roomCleanState){
    this.roomCleanState = roomCleanState;
}


public void setRoomCleanId(Long roomCleanId){
    this.roomCleanId = roomCleanId;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getRoomCleanId(){
    return roomCleanId;
}


}