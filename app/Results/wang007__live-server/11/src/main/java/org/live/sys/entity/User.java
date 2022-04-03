package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import org.live.common.constants.UserTypeConstants;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity{

 private  long serialVersionUID;

// 唯一
@Column(unique = true)
 private  String username;

@Column
 private  String password;

@Column
 private  String userType;

@Column
 private  String salt;

@Column
 private  String name;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date registerTime;

@Column
 private  int isLock;

 private  int isDelete;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date lastLoginTime;

@Column
 private  String lastLoginIp;

// 懒加载
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "group_id")
 private  Group group;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getUserType(){
    return userType;
}


public String getName(){
    return name;
}


public void setRegisterTime(Date registerTime){
    this.registerTime = registerTime;
}


public void setUsername(String username){
    this.username = username;
}


public void setIsLock(int isLock){
    this.isLock = isLock;
}


public int getIsDelete(){
    return isDelete;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public String getSalt(){
    return salt;
}


public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
}


public Date getRegisterTime(){
    return registerTime;
}


public String getUsername(){
    return username;
}


public void setGroup(Group group){
    this.group = group;
}


public void setIsDelete(int isDelete){
    this.isDelete = isDelete;
}


public String getPassword(){
    return password;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public void setSalt(String salt){
    this.salt = salt;
}


public Group getGroup(){
    return group;
}


public void setUserType(String userType){
    this.userType = userType;
}


public int getIsLock(){
    return isLock;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


}