package com.empl.mgr.dto;
 import java.io.Serializable;
public class TrainingRecordDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  boolean sex;

 private  String identity;

 private  String department;

 private  String position;

 private  String time;

 private  int state;

 private  String status;

 private  String note;

public TrainingRecordDto() {
// TODO Auto-generated constructor stub
}public TrainingRecordDto(long id, String emFullName, String emIdentity, boolean sex, String applyTime, int state, String note) {
    super();
    this.id = id;
    this.name = emFullName;
    this.identity = emIdentity;
    this.sex = sex;
    this.time = applyTime;
    this.state = state;
    this.note = note;
}public TrainingRecordDto(long id, String name, int state, String applyTime) {
    super();
    this.id = id;
    this.name = name;
    this.time = applyTime;
    this.state = state;
}
public void setName(String name){
    this.name = name;
}


public boolean isSex(){
    return sex;
}


public String getName(){
    return name;
}


public void setSex(boolean sex){
    this.sex = sex;
}


public String getDepartment(){
    return department;
}


public String getTime(){
    return time;
}


public void setDepartment(String department){
    this.department = department;
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


public void setPosition(String position){
    this.position = position;
}


public void setStatus(String status){
    this.status = status;
}


public String getPosition(){
    return position;
}


public int getState(){
    return state;
}


public String getIdentity(){
    return identity;
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
    return "TrainingRecordDto [id:" + id + ", name:" + name + ", sex:" + sex + ", identity:" + identity + ", department:" + department + ", position:" + position + ", time:" + time + ", state:" + state + ", status:" + status + ", note:" + note + "]";
}


public void setIdentity(String identity){
    this.identity = identity;
}


public void setTime(String time){
    this.time = time;
}


}