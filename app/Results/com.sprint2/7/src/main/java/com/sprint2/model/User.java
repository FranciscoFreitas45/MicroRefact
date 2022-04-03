package com.sprint2.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
// One of the mandatory annotations. This annotation indicates that this class is a entity class
@Entity
// @Table annotation is used to declare the table name
@Table(name = "User_Controller12")
public class User {

@Id
 private  String userName;

 private  String userPassword;

 private  String role;

public User() {
    // zero-parametized constructor
    super();
}public User(String userName, String userPassword, String role) {
    super();
    this.userName = userName;
    this.userPassword = userPassword;
    this.role = role;
}
public void setUserName(String userName){
    this.userName = userName;
}


public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}


public void setRole(String role){
    this.role = role;
}


public String getRole(){
    return role;
}


public String getUserName(){
    return userName;
}


public String getUserPassword(){
    return userPassword;
}


@Override
public String toString(){
    return "User [userName=" + userName + ", userPassword=" + userPassword + ", role=" + role + "]";
}


}