package com.hmm.DTO;
 import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.guest.util.Gender;
import com.hmm.guest.util.GuestState;
import com.hmm.room.entity.Room;
public class Guest {

 private  String guestId;

 private  String realName;

 private  String phone;

 private  Gender gender;

 private  String idCard;

 private  String address;

 private  GuestState state;

 private  Date registerTime;

 private  String PhotoUrl;

 private  Room room;


public String getPhone(){
    return phone;
}


public void setPhotoUrl(String photoUrl){
    PhotoUrl = photoUrl;
}


public String getIdCard(){
    return idCard;
}


@Id
public String getGuestId(){
    return guestId;
}


public Gender getGender(){
    return gender;
}


public String getRealName(){
    return realName;
}


@Temporal(TemporalType.TIMESTAMP)
@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRegisterTime(){
    return registerTime;
}


public void setRoom(Room room){
    this.room = room;
}


public GuestState getState(){
    return state;
}


@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
public Room getRoom(){
    return room;
}


public String getPhotoUrl(){
    return PhotoUrl;
}


public void setState(GuestState state){
    this.state = state;
}


public String getAddress(){
    return address;
}


public void setGuestId(String guestId){
    this.guestId = guestId;
}


}