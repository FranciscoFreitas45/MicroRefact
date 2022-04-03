package com.gs.DTO;
 import java.util.Date;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
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


public void setWorkCreatedTime(Date workCreatedTime){
    this.workCreatedTime = workCreatedTime;
}


public String getRecordId(){
    return recordId;
}


public Date getWorkCreatedTime(){
    return workCreatedTime;
}


public String getWorkStatus(){
    return workStatus;
}


public String getWorkId(){
    return workId;
}


public int getCount(){
    return count;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWorkAssignTime(Date workAssignTime){
    this.workAssignTime = workAssignTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWorkAssignTime"))

.queryParam("workAssignTime",workAssignTime)
;
restTemplate.put(builder.toUriString(),null);
}


}