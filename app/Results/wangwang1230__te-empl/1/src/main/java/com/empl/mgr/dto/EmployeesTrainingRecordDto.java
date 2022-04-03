package com.empl.mgr.dto;
 import java.io.Serializable;
public class EmployeesTrainingRecordDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  int state;

 private  String status;

 private  String time;

 private  String note;

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
public void setName(String name){
    this.name = name;
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


public void setStatus(String status){
    this.status = status;
}


public int getState(){
    return state;
}


public void setId(long id){
    this.id = id;
}


public void setState(int state){
    this.state = state;
}


public void setNote(String note){
    this.note = note;
}


@Override
public String toString(){
    return "EmployeesTrainingRecordDto [id:" + id + ", name:" + name + ", state:" + state + ", status:" + status + ", time:" + time + ", note:" + note + "]";
}


public void setTime(String time){
    this.time = time;
}


}