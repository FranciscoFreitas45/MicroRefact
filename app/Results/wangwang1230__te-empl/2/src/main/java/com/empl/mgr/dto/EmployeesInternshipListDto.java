package com.empl.mgr.dto;
 import java.io.Serializable;
public class EmployeesInternshipListDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  boolean sex;

 private  String date;

 private  String department;

 private  String position;

 private  String identity;

 private  long deptId;

 private  long positionId;

public EmployeesInternshipListDto() {
// TODO Auto-generated constructor stub
}/*
	 * 实习员工列表信息
	 */
public EmployeesInternshipListDto(long emId, String emFullName, String emIdentity, boolean emSex, String emParticipateTime, long emDeparemtn, long emPosition) {
    super();
    this.id = emId;
    this.name = emFullName;
    this.identity = emIdentity;
    this.sex = emSex;
    this.date = emParticipateTime;
    this.deptId = emDeparemtn;
    this.positionId = emPosition;
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


public void setDepartment(String department){
    this.department = department;
}


public long getDeptId(){
    return deptId;
}


public long getId(){
    return id;
}


public long getPositionId(){
    return positionId;
}


public void setPosition(String position){
    this.position = position;
}


public void setPositionId(long positionId){
    this.positionId = positionId;
}


public String getPosition(){
    return position;
}


public String getIdentity(){
    return identity;
}


public void setDeptId(long deptId){
    this.deptId = deptId;
}


public void setId(long id){
    this.id = id;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "EmployeesInternshipListDto [id:" + id + ", name:" + name + ", sex:" + sex + ", date:" + date + ", department:" + department + ", position:" + position + ", identity:" + identity + "]";
}


public void setIdentity(String identity){
    this.identity = identity;
}


}