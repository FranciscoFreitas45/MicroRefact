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
@Table(name = "acl_role")
public class Role {

@Id
@GeneratedValue
 private  Integer id;

@Column(name = "role_name")
 private  String roleName;

@Column(name = "role_description")
 private  String roleDescription;

@Column(name = "role_code")
 private  String roleCode;

@Column(name = "create_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Column(name = "create_username")
 private  String createUserName;

@Version
@Column(name = "version")
 private  Integer version;

@Column(name = "status")
 private  String status;

public Role() {
}public Role(Integer id, String roleName, String roleDescription, String roleCode, Date createTime, Date updateTime, String createUserName, Integer version, String status) {
    this.id = id;
    this.roleName = roleName;
    this.roleDescription = roleDescription;
    this.roleCode = roleCode;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.createUserName = createUserName;
    this.version = version;
    this.status = status;
}
public Integer getVersion(){
    return version;
}


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public Date getCreateTime(){
    return createTime;
}


public String getRoleName(){
    return roleName;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public Integer getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getStatus(){
    return status;
}


public String getRoleDescription(){
    return roleDescription;
}


public void setStatus(String status){
    this.status = status;
}


public void setRoleDescription(String roleDescription){
    this.roleDescription = roleDescription;
}


public Date getUpdateTime(){
    return updateTime;
}


public void setCreateUserName(String createUserName){
    this.createUserName = createUserName;
}


public String getRoleCode(){
    return roleCode;
}


public String getCreateUserName(){
    return createUserName;
}


public void setId(Integer id){
    this.id = id;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "Role [id=" + id + ", roleName=" + roleName + ", roleDescription=" + roleDescription + ", roleCode=" + roleCode + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUserName=" + createUserName + ", version=" + version + ", status=" + status + "]";
}


}