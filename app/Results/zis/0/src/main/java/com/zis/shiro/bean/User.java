package com.zis.shiro.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "acl_user")
public class User {

@Id
@GeneratedValue
 private  Integer id;

@Column(name = "user_name")
 private  String userName;

@Column(name = "real_name")
 private  String realName;

 private  String password;

 private  String salt;

@Column(name = "is_delete")
 private  String isDelete;

@Column(name = "create_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

@Column(name = "role_id")
 private  Integer roleId;

@Column(name = "company_id")
 private  Integer companyId;

public User() {
}public User(Integer id, String userName, String realName, String password, String salt, String isDelete, Date createTime, Date updateTime, Integer version, Integer roleId, Integer companyId) {
    this.id = id;
    this.userName = userName;
    this.realName = realName;
    this.password = password;
    this.salt = salt;
    this.isDelete = isDelete;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
    this.roleId = roleId;
    this.companyId = companyId;
}
public Integer getVersion(){
    return version;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setPassword(String password){
    this.password = password;
}


public Date getCreateTime(){
    return createTime;
}


public void setVersion(Integer version){
    this.version = version;
}


public String getIsDelete(){
    return isDelete;
}


public String getSalt(){
    return salt;
}


public Integer getRoleId(){
    return roleId;
}


public Integer getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getRealName(){
    return realName;
}


public void setRoleId(Integer roleId){
    this.roleId = roleId;
}


public void setIsDelete(String isDelete){
    this.isDelete = isDelete;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getPassword(){
    return password;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Integer getCompanyId(){
    return companyId;
}


public void setSalt(String salt){
    this.salt = salt;
}


public void setId(Integer id){
    this.id = id;
}


public String getUserName(){
    return userName;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "User [id=" + id + ", userName=" + userName + ", realName=" + realName + ", password=" + password + ", salt=" + salt + ", isDelete=" + isDelete + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", roleId=" + roleId + ", companyId=" + companyId + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


}