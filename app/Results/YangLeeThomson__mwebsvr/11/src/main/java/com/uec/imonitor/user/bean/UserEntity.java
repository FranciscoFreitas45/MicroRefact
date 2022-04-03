package com.uec.imonitor.user.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "auth_user")
public class UserEntity {

// 指明这个属性映射为数据库的主键
@Id
// 注解默认使用主键生成方式为自增，hibernate会为我们自动生成一个名为HABERNATE_SEQUENCE的序列
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "user_name")
 private  String userName;

@Column(name = "password")
 private  String password;

@Column(name = "name")
 private  String name;

@Column(name = "email")
 private  String email;

@Column(name = "mobile_phone")
 private  String mobilePhone;

@Column(name = "tele_phone")
 private  String telePhone;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "enabled")
 private  Integer enabled;

@Column(name = "salt")
 private  String salt;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


// 在对实体数据进行数据库添加操作之前。
@PrePersist
public void prePersist(){
    this.createTime = new Date();
}


public String getSalt(){
    return salt;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setTelePhone(String telePhone){
    this.telePhone = telePhone;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public void setEnabled(Integer enabled){
    this.enabled = enabled;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public Integer getInnerid(){
    return innerid;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getTelePhone(){
    return telePhone;
}


public Integer getEnabled(){
    return enabled;
}


public void setSalt(String salt){
    this.salt = salt;
}


public String getUserName(){
    return userName;
}


public String getEmail(){
    return email;
}


public String getMobilePhone(){
    return mobilePhone;
}


public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
}


}