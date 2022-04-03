package com.zis.oldapi.response;
 public class DepartmentQueryData {

 private  Integer id;

 private  String name;

public DepartmentQueryData() {
}public DepartmentQueryData(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}