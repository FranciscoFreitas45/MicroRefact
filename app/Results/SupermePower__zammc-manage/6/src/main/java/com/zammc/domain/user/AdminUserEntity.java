package com.zammc.domain.user;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "admin_user")
@DynamicInsert
@DynamicUpdate
public class AdminUserEntity {

 private  long userId;

 private  String userName;

 private  String passWord;

 private  String realName;

 private  Timestamp createTime;

 private  int version;

public AdminUserEntity() {
}
@Basic
@Column(name = "version")
public int getVersion(){
    return version;
}


public void setRealName(String realName){
    this.realName = realName;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "pass_word")
public String getPassWord(){
    return passWord;
}


public void setVersion(int version){
    this.version = version;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "real_name")
public String getRealName(){
    return realName;
}


public void setPassWord(String passWord){
    this.passWord = passWord;
}


public void setUserName(String userName){
    this.userName = userName;
}


@Basic
@Column(name = "user_name")
public String getUserName(){
    return userName;
}


@Override
public String toString(){
    return "AdminUserEntity{" + "userId=" + userId + ", userName='" + userName + '\'' + ", passWord='" + passWord + '\'' + ", realName='" + realName + '\'' + ", createTime=" + createTime + ", version=" + version + '}';
}


@Id
@Column(name = "user_id")
public long getUserId(){
    return userId;
}


public void setUserId(long userId){
    this.userId = userId;
}


}