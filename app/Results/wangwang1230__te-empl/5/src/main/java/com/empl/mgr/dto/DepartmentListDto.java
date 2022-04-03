package com.empl.mgr.dto;
 import java.io.Serializable;
import java.util.Date;
public class DepartmentListDto implements Serializable{

 private  long serialVersionUID;

 private  long deptId;

 private  String deptName;

 private  Date createTime;

 private  String creator;

 private  String deptDescription;

 private  long principal;

 private  String time;

 private  String fullName;

public DepartmentListDto() {
// TODO Auto-generated constructor stub
}public DepartmentListDto(long deptId, String deptName, Date createTime, String creator, String deptDescription, long deptPrincipal) {
    super();
    this.deptId = deptId;
    this.deptName = deptName;
    this.createTime = createTime;
    this.creator = creator;
    this.deptDescription = deptDescription;
    this.principal = deptPrincipal;
}
public Date getCreateTime(){
    return createTime;
}


public String getTime(){
    return time;
}


public void setCreator(String creator){
    this.creator = creator;
}


public long getDeptId(){
    return deptId;
}


public String getDeptDescription(){
    return deptDescription;
}


public void setDeptDescription(String deptDescription){
    this.deptDescription = deptDescription;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getDeptName(){
    return deptName;
}


public String getCreator(){
    return creator;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public long getPrincipal(){
    return principal;
}


public void setPrincipal(long principal){
    this.principal = principal;
}


public void setDeptId(long deptId){
    this.deptId = deptId;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


@Override
public String toString(){
    return "DepartmentListDto [deptId:" + deptId + ", deptName:" + deptName + ", createTime:" + createTime + ", creator:" + creator + ", deptDescription:" + deptDescription + ", principal:" + principal + ", time:" + time + "]";
}


public String getFullName(){
    return fullName;
}


public void setTime(String time){
    this.time = time;
}


}