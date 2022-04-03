package org.live.school.vo;
 import java.util.Date;
public class MajorVo {

 private  String id;

 private  String code;

 private  String name;

 private  String description;

 private  Date createTime;

 private  String departmentName;

 private  boolean enableFlag;

public MajorVo(String id, String code, String name, String description, Date createTime, String departmentName, boolean enableFlag) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.createTime = createTime;
    this.departmentName = departmentName;
    this.enableFlag = enableFlag;
}
public void setName(String name){
    this.name = name;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public boolean isEnableFlag(){
    return enableFlag;
}


public String getDepartmentName(){
    return departmentName;
}


public void setEnableFlag(boolean enableFlag){
    this.enableFlag = enableFlag;
}


public void setCode(String code){
    this.code = code;
}


public String getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


}