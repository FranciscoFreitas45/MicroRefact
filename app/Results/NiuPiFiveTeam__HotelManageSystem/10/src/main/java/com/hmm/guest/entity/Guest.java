package com.hmm.guest.entity;
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
@Entity
@Table(name = "t_guest")
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


public void setRealName(String realName){
    this.realName = realName;
}


public void setPhotoUrl(String photoUrl){
    PhotoUrl = photoUrl;
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


@Id
public String getGuestId(){
    return guestId;
}


public Gender getGender(){
    return gender;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getRealName(){
    return realName;
}


@Temporal(TemporalType.TIMESTAMP)
@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRegisterTime(){
    return registerTime;
}


public void setGender(Gender gender){
    this.gender = gender;
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


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setState(GuestState state){
    this.state = state;
}


public String getAddress(){
    return address;
}


@Override
public String toString(){
    return "Guest [guestId=" + guestId + ", realName=" + realName + ", phone=" + phone + ", gender=" + gender + ", idCard=" + idCard + ", address=" + address + ", state=" + state + ", registerTime=" + registerTime + ", PhotoUrl=" + PhotoUrl + "]";
}


public void setGuestId(String guestId){
    this.guestId = guestId;
}


}