package com.empl.mgr.DTO;
 import java.io.Serializable;
public class EmployeesTrainingRecordDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  int state;

 private  String status;

 private  String time;

 private  String note;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public EmployeesTrainingRecordDto() {
// TODO Auto-generated constructor stub
}public EmployeesTrainingRecordDto(long logId, String name, int state, String applyTime, String note) {
    super();
    this.id = logId;
    this.name = name;
    this.state = state;
    this.time = applyTime;
    this.note = note;
}
public String getName(){
    return name;
}


public String getTime(){
    return time;
}


public long getId(){
    return id;
}


public String getNote(){
    return note;
}


public String getStatus(){
    return status;
}


public int getState(){
    return state;
}


public void setState(int state){
    this.state = state;
}


@Override
public String toString(){
    return "EmployeesTrainingRecordDto [id:" + id + ", name:" + name + ", state:" + state + ", status:" + status + ", time:" + time + ", note:" + note + "]";
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


}