package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
public class UserDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String userName;

 private  String email;

 private  String sex;

 private  Date birthday;

public UserDTO() {
}
public void setEmail(String email){
    this.email = email;
}


public Date getBirthday(){
    return birthday;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setSex(String sex){
    this.sex = sex;
}


public String getSex(){
    return sex;
}


public String getUserName(){
    return userName;
}


public String getEmail(){
    return email;
}


public void setBirthday(Date birthday){
    this.birthday = birthday;
}


}