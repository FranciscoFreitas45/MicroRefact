package org.live.live.vo;
 import javax.persistence.Column;
public class LiveRoomInfoVo {

 private  String categoryName;

 private  String roomNum;

 private  String roomName;

 private  String roomLabel;

 private  long historyMaxOnlineCount;

 private  String description;

 private  String account;

 private  String nickname;

 private  String sex;

 private  String headImgUrl;

public LiveRoomInfoVo() {
}public LiveRoomInfoVo(String categoryName, String roomNum, String roomName, String roomLabel, long historyMaxOnlineCount, String description, String account, String nickname, String sex, String headImgUrl) {
    this.categoryName = categoryName;
    this.roomNum = roomNum;
    this.roomName = roomName;
    this.roomLabel = roomLabel;
    this.historyMaxOnlineCount = historyMaxOnlineCount;
    this.description = description;
    this.account = account;
    this.nickname = nickname;
    this.sex = sex;
    this.headImgUrl = headImgUrl;
}
public void setSex(String sex){
    this.sex = sex;
}


public String getCategoryName(){
    return categoryName;
}


public void setHistoryMaxOnlineCount(long historyMaxOnlineCount){
    this.historyMaxOnlineCount = historyMaxOnlineCount;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public String getNickname(){
    return nickname;
}


public String getAccount(){
    return account;
}


public long getHistoryMaxOnlineCount(){
    return historyMaxOnlineCount;
}


public String getSex(){
    return sex;
}


public String getRoomNum(){
    return roomNum;
}


public void setAccount(String account){
    this.account = account;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getRoomLabel(){
    return roomLabel;
}


public void setRoomLabel(String roomLabel){
    this.roomLabel = roomLabel;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public String getRoomName(){
    return roomName;
}


}