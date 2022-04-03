package com.empl.mgr.dto;
 import java.io.Serializable;
public class EmployeesCompnayDto implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  String state;

 private  String end;

 private  String jobs;

 private  String reason;

public EmployeesCompnayDto() {
// TODO Auto-generated constructor stub
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getState(){
    return state;
}


public void setJobs(String jobs){
    this.jobs = jobs;
}


public String getReason(){
    return reason;
}


public void setReason(String reason){
    this.reason = reason;
}


public void setState(String state){
    this.state = state;
}


@Override
public String toString(){
    return "EmployeesCompnayDto [name:" + name + ", state:" + state + ", end:" + end + ", jobs:" + jobs + ", reason:" + reason + "]";
}


public void setEnd(String end){
    this.end = end;
}


public String getEnd(){
    return end;
}


public String getJobs(){
    return jobs;
}


}