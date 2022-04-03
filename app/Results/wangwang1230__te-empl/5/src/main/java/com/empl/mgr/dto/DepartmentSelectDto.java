package com.empl.mgr.dto;
 import java.io.Serializable;
public class DepartmentSelectDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

public DepartmentSelectDto() {
// TODO Auto-generated constructor stub
}public DepartmentSelectDto(long deptId, String deptName) {
    super();
    this.id = deptId;
    this.name = deptName;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


}