package com.gbcom.system.domain.base;
 import com.hc.core.entity.Auditable;
import java.io.Serializable;
public class BaseSysRole implements Auditable,Serializable{

 public  String REF;

 public  String PROP_ROLE_NAME;

 public  String PROP_CREATE_USER;

 public  String PROP_DESCRIPTION;

 public  String PROP_CREATE_TIME;

 public  String PROP_UPDATE_TIME;

 public  String PROP_ID;

 public  String PROP_CODE;

 public  String PROP_UPDATE_USER;

 private  int hashCode;

 private  java.lang.Long id;

 private  Integer roleType;

 private  Integer viewType;

 private  Integer domainType;

 private  java.lang.String code;

 private  java.lang.String roleName;

 private  java.lang.String description;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String createUser;

 private  java.lang.String updateUser;

 private  java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges;

 private  java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles;

// constructors
public BaseSysRole() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysRole(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public java.lang.String getCreateUser(){
    return createUser;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public void setRoleName(java.lang.String roleName){
    this.roleName = roleName;
}


public Integer getDomainType(){
    return domainType;
}


public java.lang.Long getId(){
    return id;
}


public void setDescription(java.lang.String description){
    this.description = description;
}


public java.lang.String getDescription(){
    return description;
}


public java.util.Set<com.gbcom.system.domain.SysUserRole> getSysUserRoles(){
    if (sysUserRoles == null) {
        sysUserRoles = new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>();
    }
    return sysUserRoles;
}


public Integer getViewType(){
    return viewType;
}


public int hashCode(){
    if (Integer.MIN_VALUE == this.hashCode) {
        if (null == this.getId())
            return super.hashCode();
        else {
            String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
            this.hashCode = hashStr.hashCode();
        }
    }
    return this.hashCode;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setViewType(Integer viewType){
    this.viewType = viewType;
}


public java.lang.String getCode(){
    return code;
}


public void addTosysUserRoles(com.gbcom.system.domain.SysUserRole sysUserRole){
    if (null == getSysUserRoles())
        setSysUserRoles(new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>());
    getSysUserRoles().add(sysUserRole);
}


public void setDomainType(Integer domainType){
    this.domainType = domainType;
}


public java.lang.String getRoleName(){
    return roleName;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public java.util.Set<com.gbcom.system.domain.SysRolePrivilege> getSysRolePrivileges(){
    if (sysRolePrivileges == null) {
        sysRolePrivileges = new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>();
    }
    return sysRolePrivileges;
}


public Integer getRoleType(){
    return roleType;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public void addTosysRolePrivileges(com.gbcom.system.domain.SysRolePrivilege sysRolePrivilege){
    if (null == getSysRolePrivileges())
        setSysRolePrivileges(new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>());
    getSysRolePrivileges().add(sysRolePrivilege);
}


public void setSysUserRoles(java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles){
    this.sysUserRoles = sysUserRoles;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
}


public java.lang.String getUpdateUser(){
    return updateUser;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysRole))
        return false;
    else {
        com.gbcom.system.domain.SysRole sysRole = (com.gbcom.system.domain.SysRole) obj;
        if (null == this.getId() || null == sysRole.getId())
            return false;
        else
            return (this.getId().equals(sysRole.getId()));
    }
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(roleName);
    builder.append(description);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(createUser);
    builder.append(updateUser);
    return builder.toString();
}


public void initialize(){
}


public void setSysRolePrivileges(java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges){
    this.sysRolePrivileges = sysRolePrivileges;
}


public void setRoleType(Integer roleType){
    this.roleType = roleType;
}


}