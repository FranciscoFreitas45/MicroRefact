package com.hmm.guest.dto;
 import java.util.Date;
import com.hmm.guest.util.Gender;
import com.hmm.guest.util.GuestState;
import com.hmm.room.entity.Room;
import com.hmm.room.util.RoomState;
import com.hmm.room.util.RoomType;
public class GuestInfoDto {

 private  String realName;

 private  String phone;

 private  Gender gender;

 private  String idCard;

 private  String address;

 private  GuestState guestState;

 private  Date registerTime;

 private  String roomNo;

 private  RoomType type;

 private  RoomState roomState;

 private  String roomPass;


public String getPhone(){
    return phone;
}


public void setRealName(String realName){
    this.realName = realName;
}


public String getRoomPass(){
    return roomPass;
}


public void setRegisterTime(Date registerTime){
    this.registerTime = registerTime;
}


public String getIdCard(){
    return idCard;
}


public void setAddress(String address){
    this.address = address;
}


public RoomState getRoomState(){
    return roomState;
}


public void setPhone(String phone){
    this.phone = phone;
}


public Gender getGender(){
    return gender;
}


public void setType(RoomType type){
    this.type = type;
}


public void setRoomState(RoomState roomState){
    this.roomState = roomState;
}


public String getRealName(){
    return realName;
}


public void setGuestState(GuestState guestState){
    this.guestState = guestState;
}


public Date getRegisterTime(){
    return registerTime;
}


public void setGender(Gender gender){
    this.gender = gender;
}


public void setRoomPass(String roomPass){
    this.roomPass = roomPass;
}


public RoomType getType(){
    return type;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public String getAddress(){
    return address;
}


@Override
public String toString(){
    return "GuestInfoDto [realName=" + realName + ", phone=" + phone + ", gender=" + gender + ", idCard=" + idCard + ", address=" + address + ", guestState=" + guestState + ", registerTime=" + registerTime + ", roomNo=" + roomNo + ", type=" + type + ", roomState=" + roomState + ", roomPass=" + roomPass + "]";
}


public void setRoomNo(String roomNo){
    this.roomNo = roomNo;
}


public String getRoomNo(){
    return roomNo;
}


public GuestState getGuestState(){
    return guestState;
}


}