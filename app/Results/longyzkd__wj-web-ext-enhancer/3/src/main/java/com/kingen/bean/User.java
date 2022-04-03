package com.kingen.bean;
 import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.google.common.collect.Lists;
@Entity
@Table(name = "tmanageruser")
public class User {

 private  String userId;

 private  String username;

 private  String password;

 private  Integer userType;

 private  Boolean isStoped;

 private  String note;

 private  String phone;

 private  String avatarPath;

 private  String salt;

 private  String mail;

 private  String status;

 private  String deptName;

// Constructors
/**
 * default constructor
 */
public User() {
}/**
 * full constructor
 */
public User(String userName, String password, Integer userType, Boolean isStoped, String note, String phone) {
    this.username = userName;
    this.password = password;
    this.userType = userType;
    this.isStoped = isStoped;
    this.note = note;
    this.phone = phone;
}
@Column(name = "Phone", length = 100)
public String getPhone(){
    return this.phone;
}


public void setPassword(String password){
    this.password = password;
}


@Column(name = "AvatarPath", length = 100)
public String getAvatarPath(){
    return avatarPath;
}


@Column(name = "UserType")
public Integer getUserType(){
    return this.userType;
}


public void setUsername(String username){
    this.username = username;
}


@Column(name = "salt")
public String getSalt(){
    return salt;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Column(name = "Note", length = 2000)
public String getNote(){
    return this.note;
}


@Column(name = "status")
public String getStatus(){
    return status;
}


@Column(name = "deptname")
public String getDeptName(){
    return deptName;
}


public void setStatus(String status){
    this.status = status;
}


@Column(name = "UserName", length = 100)
public String getUsername(){
    return username;
}


@Column(name = "mail")
public String getMail(){
    return mail;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


@Column(name = "Password", length = 200)
public String getPassword(){
    return this.password;
}


public void setMail(String mail){
    this.mail = mail;
}


public void setUserType(Integer userType){
    this.userType = userType;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setNote(String note){
    this.note = note;
}


@Column(name = "IsStoped")
public Boolean getIsStoped(){
    return this.isStoped;
}


public void setAvatarPath(String avatarPath){
    this.avatarPath = avatarPath;
}


@Id
@Column(name = "UserID", unique = true, nullable = false, length = 100)
public String getUserId(){
    return this.userId;
}


public void setIsStoped(Boolean isStoped){
    this.isStoped = isStoped;
}


public void setUserId(String userId){
    this.userId = userId;
}


}