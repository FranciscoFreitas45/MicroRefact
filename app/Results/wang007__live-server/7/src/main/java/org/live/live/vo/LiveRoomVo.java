package org.live.live.vo;
 public class LiveRoomVo {

 private  String liveroomId;

 private  String roomNum;

 private  String roomName;

 private  String liveRoomCoverUrl;

 private  String liveRoomUrl;

 private  String nickname;

 private  int onlineCount;

 private  boolean liveFlag;

 private  boolean banLiveFlag;

public LiveRoomVo() {
}public LiveRoomVo(String liveroomId, String roomNum, String roomName, String liveRoomCoverUrl, String liveRoomUrl, String nickname, int onlineCount, boolean liveFlag, boolean banLiveFlag) {
    this.liveroomId = liveroomId;
    this.roomNum = roomNum;
    this.roomName = roomName;
    this.liveRoomCoverUrl = liveRoomCoverUrl;
    this.liveRoomUrl = liveRoomUrl;
    this.nickname = nickname;
    this.onlineCount = onlineCount;
    this.liveFlag = liveFlag;
    this.banLiveFlag = banLiveFlag;
}
public void setOnlineCount(int onlineCount){
    this.onlineCount = onlineCount;
}


public boolean isBanLiveFlag(){
    return banLiveFlag;
}


public String getLiveRoomCoverUrl(){
    return liveRoomCoverUrl;
}


public void setLiveroomId(String liveroomId){
    this.liveroomId = liveroomId;
}


public void setBanLiveFlag(boolean banLiveFlag){
    this.banLiveFlag = banLiveFlag;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public boolean isLiveFlag(){
    return liveFlag;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public String getNickname(){
    return nickname;
}


public int getOnlineCount(){
    return onlineCount;
}


public void setLiveRoomUrl(String liveRoomUrl){
    this.liveRoomUrl = liveRoomUrl;
}


public void setLiveFlag(boolean liveFlag){
    this.liveFlag = liveFlag;
}


public String getLiveRoomUrl(){
    return liveRoomUrl;
}


public String getRoomNum(){
    return roomNum;
}


public void setLiveRoomCoverUrl(String liveRoomCoverUrl){
    this.liveRoomCoverUrl = liveRoomCoverUrl;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getLiveroomId(){
    return liveroomId;
}


public String getRoomName(){
    return roomName;
}


}