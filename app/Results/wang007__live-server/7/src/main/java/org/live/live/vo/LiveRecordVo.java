package org.live.live.vo;
 import java.util.Date;
public class LiveRecordVo {

 private  String id;

 private  String recordNum;

 private  String roomName;

 private  String roomNum;

 private  int maxOnlineCount;

 private  Date startTime;

 private  Date endTime;

public LiveRecordVo() {
}public LiveRecordVo(String id, String recordNum, String roomName, String roomNum, int maxOnlineCount, Date startTime, Date endTime) {
    this.id = id;
    this.recordNum = recordNum;
    this.roomName = roomName;
    this.roomNum = roomNum;
    this.maxOnlineCount = maxOnlineCount;
    this.startTime = startTime;
    this.endTime = endTime;
}
public String getId(){
    return id;
}


public void setRecordNum(String recordNum){
    this.recordNum = recordNum;
}


public Date getEndTime(){
    return endTime;
}


public void setRoomName(String roomName){
    this.roomName = roomName;
}


public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
}


public void setMaxOnlineCount(int maxOnlineCount){
    this.maxOnlineCount = maxOnlineCount;
}


public int getMaxOnlineCount(){
    return maxOnlineCount;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setId(String id){
    this.id = id;
}


public String getRoomNum(){
    return roomNum;
}


public String getRecordNum(){
    return recordNum;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public String getRoomName(){
    return roomName;
}


}