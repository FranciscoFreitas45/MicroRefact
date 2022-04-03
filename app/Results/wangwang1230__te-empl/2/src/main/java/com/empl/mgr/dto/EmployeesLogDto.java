package com.empl.mgr.dto;
 public class EmployeesLogDto {

 private  long id;

 private  String note;

 private  String creator;

 private  String type;

 private  String time;

public EmployeesLogDto() {
// TODO Auto-generated constructor stub
}
public String getType(){
    return type;
}


public String getTime(){
    return time;
}


public void setCreator(String creator){
    this.creator = creator;
}


public void setId(long id){
    this.id = id;
}


public void setNote(String note){
    this.note = note;
}


public long getId(){
    return id;
}


@Override
public String toString(){
    return "Demo [id:" + id + ", note:" + note + ", creator:" + creator + ", type:" + type + ", time:" + time + "]";
}


public String getNote(){
    return note;
}


public void setType(String type){
    this.type = type;
}


public String getCreator(){
    return creator;
}


public void setTime(String time){
    this.time = time;
}


}