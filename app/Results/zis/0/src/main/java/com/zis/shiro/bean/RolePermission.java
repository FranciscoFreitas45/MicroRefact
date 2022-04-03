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
@Table(name = "acl_role_permission")
public class RolePermission {

@Id
@GeneratedValue
 private  Integer id;

@Column(name = "role_id")
 private  Integer roleId;

@Column(name = "permission_id")
 private  Integer permissionId;

@Column(name = "status")
 private  String status;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time", updatable = false)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "update_time")
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

public RolePermission() {
}public RolePermission(Integer id, Integer roleId, Integer permissionId, String status, Date createTime, Date updateTime, Integer version) {
    this.id = id;
    this.roleId = roleId;
    this.permissionId = permissionId;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
}
public Integer getVersion(){
    return version;
}


public Date getCreateTime(){
    return createTime;
}


public void setVersion(Integer version){
    this.version = version;
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


public void setPermissionId(Integer permissionId){
    this.permissionId = permissionId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public void setRoleId(Integer roleId){
    this.roleId = roleId;
}


public Date getUpdateTime(){
    return updateTime;
}


public void setId(Integer id){
    this.id = id;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "RolePermission [id=" + id + ", roleId=" + roleId + ", permissionId=" + permissionId + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + "]";
}


public Integer getPermissionId(){
    return permissionId;
}


}