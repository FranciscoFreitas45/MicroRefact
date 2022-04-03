package org.live.app.vo;
 public class AppLiveRoomVo {

 private  String liveRoomId;

 private  String anchorId;

 private  String roomNum;

 private  String liveRoomCoverUrl;

 private  String liveRoomUrl;

 private  String liveRoomName;

 private  String anchorName;

 private  String headImgUrl;

 private  int onlineCount;

 private  boolean liveFlag;

public AppLiveRoomVo() {
}public AppLiveRoomVo(String liveRoomId, String anchorId, String roomNum, String liveRoomCoverUrl, String liveRoomUrl, String liveRoomName, String anchorName, String headImgUrl, int onlineCount, boolean liveFlag) {
    this.liveRoomId = liveRoomId;
    this.anchorId = anchorId;
    this.roomNum = roomNum;
    this.liveRoomCoverUrl = liveRoomCoverUrl;
    this.liveRoomUrl = liveRoomUrl;
    this.liveRoomName = liveRoomName;
    this.anchorName = anchorName;
    this.headImgUrl = headImgUrl;
    this.onlineCount = onlineCount;
    this.liveFlag = liveFlag;
}
public void setLiveRoomName(String liveRoomName){
    this.liveRoomName = liveRoomName;
}


public void setOnlineCount(int onlineCount){
    this.onlineCount = onlineCount;
}


public String getLiveRoomName(){
    return liveRoomName;
}


public String getLiveRoomCoverUrl(){
    return liveRoomCoverUrl;
}


public void setAnchorName(String anchorName){
    this.anchorName = anchorName;
}


public String getAnchorId(){
    return anchorId;
}


public String getLiveRoomId(){
    return liveRoomId;
}


public boolean isLiveFlag(){
    return liveFlag;
}


public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public void setLiveRoomId(String liveRoomId){
    this.liveRoomId = liveRoomId;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public int getOnlineCount(){
    return onlineCount;
}


public void setLiveFlag(boolean liveFlag){
    this.liveFlag = liveFlag;
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
}


public String getAnchorName(){
    return anchorName;
}


public String getLiveRoomUrl(){
    return liveRoomUrl;
}


public void setLiveRoomCoverUrl(String liveRoomCoverUrl){
    this.liveRoomCoverUrl = liveRoomCoverUrl;
}


public String getRoomNum(){
    return roomNum;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public void setAnchorId(String anchorId){
    this.anchorId = anchorId;
}


}