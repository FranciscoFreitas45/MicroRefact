package com.gs.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


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


public void setRecordId(String recordId){
    this.recordId = recordId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRecordId"))

.queryParam("recordId",recordId)
;
restTemplate.put(builder.toUriString(),null);
}


}