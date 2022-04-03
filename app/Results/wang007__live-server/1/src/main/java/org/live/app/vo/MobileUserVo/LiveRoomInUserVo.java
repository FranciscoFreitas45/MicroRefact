package org.live.app.vo.MobileUserVo;
 import java.util.Date;
public class LiveRoomInUserVo {

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


public String getRoomId(){
    return roomId;
}


public String getCategoryId(){
    return categoryId;
}


public String getCategoryName(){
    return categoryName;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public void setRoomCoverUrl(String roomCoverUrl){
    this.roomCoverUrl = roomCoverUrl;
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


public void setBanLiveFlag(boolean banLiveFlag){
    this.banLiveFlag = banLiveFlag;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
}


public void setCategoryId(String categoryId){
    this.categoryId = categoryId;
}


public String getLiveRoomUrl(){
    return liveRoomUrl;
}


public String getRoomNum(){
    return roomNum;
}


public String getRoomLabel(){
    return roomLabel;
}


public void setRoomLabel(String roomLabel){
    this.roomLabel = roomLabel;
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


}