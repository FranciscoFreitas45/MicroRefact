package org.live.app.vo;
 import java.util.Date;
public class MobileUserVo {

 private  String userId;

 private  String account;

 private  String password;

 private  String nickname;

 private  String headImgUrl;

 private  String email;

 private  String mobileNumber;

 private  String realName;

 private  String sex;

 private  Date birthday;

 private  boolean anchorFlag;

 private  LiveRoomInUserVo liveRoomVo;

 private  String categoryId;

 private  String categoryName;

 private  String roomId;

 private  String roomNum;

 private  String roomName;

 private  String roomCoverUrl;

 private  String liveRoomUrl;

 private  String roomLabel;

 private  boolean banLiveFlag;

 private  String description;


public void setPassword(String password){
    this.password = password;
}


public String getMobileNumber(){
    return mobileNumber;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public String getCategoryName(){
    return categoryName;
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


public boolean isBanLiveFlag(){
    return banLiveFlag;
}


public void setAnchorFlag(boolean anchorFlag){
    this.anchorFlag = anchorFlag;
}


public void setLiveRoomVo(LiveRoomInUserVo liveRoomVo){
    this.liveRoomVo = liveRoomVo;
}


public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public void setCategoryId(String categoryId){
    this.categoryId = categoryId;
}


public String getAccount(){
    return account;
}


public String getLiveRoomUrl(){
    return liveRoomUrl;
}


public LiveRoomInUserVo newInstantLiveRoomVo(){
    return new LiveRoomInUserVo();
}


public String getRoomLabel(){
    return roomLabel;
}


public void setRoomLabel(String roomLabel){
    this.roomLabel = roomLabel;
}


public LiveRoomInUserVo getLiveRoomVo(){
    return liveRoomVo;
}


public boolean isAnchorFlag(){
    return anchorFlag;
}


public String getRoomId(){
    return roomId;
}


public Date getBirthday(){
    return birthday;
}


public void setSex(String sex){
    this.sex = sex;
}


public String getCategoryId(){
    return categoryId;
}


public void setRoomCoverUrl(String roomCoverUrl){
    this.roomCoverUrl = roomCoverUrl;
}


public String getRealName(){
    return realName;
}


public void setBirthday(Date birthday){
    this.birthday = birthday;
}


public void setBanLiveFlag(boolean banLiveFlag){
    this.banLiveFlag = banLiveFlag;
}


public String getPassword(){
    return password;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
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


public String getEmail(){
    return email;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public String getRoomCoverUrl(){
    return roomCoverUrl;
}


public void setRoomId(String roomId){
    this.roomId = roomId;
}


public String getRoomName(){
    return roomName;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}