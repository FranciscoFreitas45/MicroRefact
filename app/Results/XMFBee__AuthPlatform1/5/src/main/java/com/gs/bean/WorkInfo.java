package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.User;
public class WorkInfo {

 private  String workId;

 private  String recordId;

 private  String userId;

 private  Date workAssignTime;

 private  Date workCreatedTime;

 private  String workStatus;

 private  int count;

 private  int week;

 private  MaintainRecord maintainRecord;

 private  User user;


public void setWorkId(String workId){
    this.workId = workId;
}


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public void setWeek(int week){
    this.week = week;
}


public User getUser(){
    return user;
}


public Date getWorkAssignTime(){
    return workAssignTime;
}


public int getWeek(){
    return week;
}


public void setWorkStatus(String workStatus){
    this.workStatus = workStatus;
}


public void setWorkCreatedTime(Date workCreatedTime){
    this.workCreatedTime = workCreatedTime;
}


public void setMaintainRecord(MaintainRecord maintainRecord){
    this.maintainRecord = maintainRecord;
}


public String getRecordId(){
    return recordId;
}


public void setWorkAssignTime(Date workAssignTime){
    this.workAssignTime = workAssignTime;
}


public Date getWorkCreatedTime(){
    return workCreatedTime;
}


public String getWorkStatus(){
    return workStatus;
}


@Override
public String toString(){
    return "WorkInfo{" + "workId='" + workId + '\'' + ", recordId='" + recordId + '\'' + ", userId='" + userId + '\'' + ", workAssignTime=" + workAssignTime + ", workCreatedTime=" + workCreatedTime + ", workStatus='" + workStatus + '\'' + ", count=" + count + ", maintainRecord=" + maintainRecord + ", user=" + user + '}';
}


public String getWorkId(){
    return workId;
}


public void setUser(User user){
    this.user = user;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}