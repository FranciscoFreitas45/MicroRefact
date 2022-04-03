package com.circle.pojo.receive;
 public class ReceiveInfo {

 private  int id;

 private  int userId;

 private  String receiveName;

 private  String receivePhone;

 private  String receiveAddress;

 private  int isDefault;


public void setReceiveAddress(String receiveAddress){
    this.receiveAddress = receiveAddress;
}


public int getIsDefault(){
    return isDefault;
}


public void setIsDefault(int isDefault){
    this.isDefault = isDefault;
}


public void setReceiveName(String receiveName){
    this.receiveName = receiveName;
}


public void setId(int id){
    this.id = id;
}


public void setReceivePhone(String receivePhone){
    this.receivePhone = receivePhone;
}


public int getId(){
    return id;
}


public String getReceivePhone(){
    return receivePhone;
}


public String getReceiveName(){
    return receiveName;
}


public String getReceiveAddress(){
    return receiveAddress;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}