package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
public class TeRole {

 private  long serialVersionUID;

 private  long roleId;

 private  Date timestamp;

 private  String roleName;

 private  String roleDescription;

 private  String roleLabel;

 private  Date createTime;

 private  String creator;

// Constructors
/**
 * default constructor
 */
public TeRole() {
}/**
 * full constructor
 */
public TeRole(String roleName, String roleDescription, String roleLabel, Date createTime, String creator) {
    this.roleName = roleName;
    this.roleDescription = roleDescription;
    this.roleLabel = roleLabel;
    this.createTime = createTime;
    this.creator = creator;
}
@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "roleName", length = 128)
public String getRoleName(){
    return this.roleName;
}


@Column(name = "roleLabel", unique = true, length = 64)
public String getRoleLabel(){
    return this.roleLabel;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "roleId", unique = true, nullable = false)
public long getRoleId(){
    return this.roleId;
}


@Column(name = "roleDescription", length = 1024)
public String getRoleDescription(){
    return this.roleDescription;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


}