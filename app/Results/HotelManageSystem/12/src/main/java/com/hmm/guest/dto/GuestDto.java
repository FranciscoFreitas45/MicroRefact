package com.hmm.guest.dto;
 import com.hmm.guest.util.Gender;
import com.hmm.guest.util.GuestState;
public class GuestDto {

 private  String realName;

 private  String phone;

 private  Gender gender;

 private  String idCard;

 private  GuestState state;

public GuestDto(String realName, String idCard, String phone, Gender gender, GuestState state) {
    super();
    this.realName = realName;
    this.idCard = idCard;
    this.phone = phone;
    this.gender = gender;
    this.state = state;
}
public String getPhone(){
    return phone;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setGender(Gender gender){
    this.gender = gender;
}


public GuestState getState(){
    return state;
}


public String getIdCard(){
    return idCard;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setState(GuestState state){
    this.state = state;
}


public void setPhone(String phone){
    this.phone = phone;
}


public Gender getGender(){
    return gender;
}


public String getRealName(){
    return realName;
}


}