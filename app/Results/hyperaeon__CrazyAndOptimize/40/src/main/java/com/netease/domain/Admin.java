package com.netease.domain;
 import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@Entity(name = "admin")
public class Admin {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Long id;

@Column(name = "username", nullable = false, length = 100)
 private  String username;

@Column(name = "password", length = 100)
 private  String password;

@Column(name = "salt", length = 50)
 private  String salt;

@Column(name = "roles")
@Type(type = "text")
 private  String roles;

@Column(name = "realname", length = 100)
 private  String realname;

@Column(name = "mobile", length = 50)
 private  String mobile;

@Column(name = "email", length = 50)
 private  String email;

@Column(name = "memo")
@Type(type = "text")
 private  String memo;

@Column(name = "last_login")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime lastLogin;

@Column(name = "over_date")
 private  Timestamp over_date;

@CreatedDate
@Column(name = "created_at")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime createdAt;

@LastModifiedDate
@Column(name = "updated_at")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime updatedAt;

@LastModifiedDate
@Column(name = "deadline")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime deadline;


public void setPassword(String password){
    this.password = password;
}


public void setLastLogin(DateTime lastLogin){
    this.lastLogin = lastLogin;
}


public void setOver_date(Timestamp over_date){
    this.over_date = over_date;
}


public String getSalt(){
    return salt;
}


public Long getId(){
    return id;
}


public void setDeadline(DateTime deadline){
    this.deadline = deadline;
}


public String getUsername(){
    return username;
}


public DateTime getDeadline(){
    return deadline;
}


public void setCreatedAt(DateTime createdAt){
    this.createdAt = createdAt;
}


public DateTime getCreatedAt(){
    return createdAt;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setId(Long id){
    this.id = id;
}


public void setRoles(String roles){
    this.roles = roles;
}


public DateTime getLastLogin(){
    return lastLogin;
}


public void setUpdatedAt(DateTime updatedAt){
    this.updatedAt = updatedAt;
}


public String getRoles(){
    return roles;
}


public void setUsername(String username){
    this.username = username;
}


public DateTime getUpdatedAt(){
    return updatedAt;
}


public Timestamp getOver_date(){
    return over_date;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getPassword(){
    return password;
}


public void setRealname(String realname){
    this.realname = realname;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getMemo(){
    return memo;
}


public void setEmail(String email){
    this.email = email;
}


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


public String getRealname(){
    return realname;
}


}