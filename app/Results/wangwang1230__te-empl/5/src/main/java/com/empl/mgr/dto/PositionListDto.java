package com.empl.mgr.dto;
 import java.io.Serializable;
import java.util.Date;
public class PositionListDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String positionName;

 private  long department;

 private  String description;

 private  String creator;

 private  Date date;

 private  String departmentName;

 private  String time;

public PositionListDto() {
// TODO Auto-generated constructor stub
}public PositionListDto(long poId, String poName, long poDepartment, String poDescription, String creator, Date createTime, String deptName) {
    super();
    this.id = poId;
    this.positionName = poName;
    this.department = poDepartment;
    this.description = poDescription;
    this.creator = creator;
    this.date = createTime;
    this.departmentName = deptName;
}
public long getDepartment(){
    return department;
}


public String getDepartmentName(){
    return departmentName;
}


public String getTime(){
    return time;
}


public void setDepartment(long department){
    this.department = department;
}


public void setCreator(String creator){
    this.creator = creator;
}


public long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getCreator(){
    return creator;
}


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


public void setPositionName(String positionName){
    this.positionName = positionName;
}


public void setId(long id){
    this.id = id;
}


public void setDate(Date date){
    this.date = date;
}


public Date getDate(){
    return date;
}


@Override
public String toString(){
    return "PositionListDto [id:" + id + ", positionName:" + positionName + ", department:" + department + ", description:" + description + ", creator:" + creator + ", date:" + date + ", departmentName:" + departmentName + "]";
}


public String getPositionName(){
    return positionName;
}


public void setTime(String time){
    this.time = time;
}


}