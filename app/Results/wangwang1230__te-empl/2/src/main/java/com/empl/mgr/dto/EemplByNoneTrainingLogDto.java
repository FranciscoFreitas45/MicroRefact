package com.empl.mgr.dto;
 import java.io.Serializable;
public class EemplByNoneTrainingLogDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  long emplDept;

 private  String name;

 private  boolean sex;

 private  String identity;

 private  String birthday;

 private  String department;

public EemplByNoneTrainingLogDto() {
// TODO Auto-generated constructor stub
}public EemplByNoneTrainingLogDto(long emId, String emFullName, boolean emSex, String emIdentity, String emBirthday, long emDeparemtn) {
    super();
    this.id = emId;
    this.name = emFullName;
    this.sex = emSex;
    this.identity = emIdentity;
    this.birthday = emBirthday;
    this.emplDept = emDeparemtn;
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


public String getBirthday(){
    return birthday;
}


public String getDepartment(){
    return department;
}


public void setSex(boolean sex){
    this.sex = sex;
}


public void setDepartment(String department){
    this.department = department;
}


public void setEmplDept(long emplDept){
    this.emplDept = emplDept;
}


public long getId(){
    return id;
}


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public long getEmplDept(){
    return emplDept;
}


public String getIdentity(){
    return identity;
}


public void setId(long id){
    this.id = id;
}


@Override
public String toString(){
    return "EemplByNoneTrainingLogDto [id:" + id + ", emplDept:" + emplDept + ", name:" + name + ", sex:" + sex + ", identity:" + identity + ", birthday:" + birthday + ", department:" + department + "]";
}


public void setIdentity(String identity){
    this.identity = identity;
}


}