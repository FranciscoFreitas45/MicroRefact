package com.netease.domain;
 import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@Entity(name = "New_User")
public class New_User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Integer id;

@Column(name = "password", length = 20, nullable = false)
 private  String password;

@Column(name = "passwordsha", length = 256, nullable = false)
 private  String passwordsha;

@Column(name = "salt", length = 128, nullable = false)
 private  String salt;

@Column(name = "mobile_number")
 private  String mobileNumber;

@Column(name = "roles", length = 255)
@Type(type = "text")
 private  String roles;

@CreatedDate
@Column(name = "create_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime create_time;

@LastModifiedDate
@Column(name = "update_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime update_time;


public void setPassword(String password){
    this.password = password;
}


public String getMobileNumber(){
    return mobileNumber;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public DateTime getUpdate_time(){
    return update_time;
}


public void setPasswordsha(String passwordsha){
    this.passwordsha = passwordsha;
}


public String getSalt(){
    return salt;
}


public Integer getId(){
    return id;
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public DateTime getCreate_time(){
    return create_time;
}


public String getPassword(){
    return password;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setId(Integer id){
    this.id = id;
}


public String getPasswordsha(){
    return passwordsha;
}


public void setRoles(String roles){
    this.roles = roles;
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
}


public String getRoles(){
    return roles;
}


}