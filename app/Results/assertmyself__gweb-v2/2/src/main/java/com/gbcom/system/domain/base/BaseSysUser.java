package com.gbcom.system.domain.base;
 import com.gbcom.system.domain.SysUserPrivilege;
import java.io.Serializable;
import com.gbcom.Interface.SysPerson;
import com.gbcom.Interface.SysArea;
import com.gbcom.Interface.SysCodeDetail;
public class BaseSysUser implements Serializable{

 public  String REF;

 public  String PROP_USER_TYPE;

 public  String PROP_LOGIN_NAME;

 public  String PROP_REASON_DESC;

 public  String PROP_PASSWORD;

 public  String PROP_UPDATE_TIME;

 public  String PROP_DISPLAY_NAME;

 public  String PROP_STATUS;

 public  String PROP_CREATE_USER;

 public  String PROP_CREATE_TIME;

 public  String PROP_OPEN_DATE;

 public  String PROP_ID;

 public  String PROP_CLOSE_DATE;

 public  String PROP_PERSON;

 public  String PROP_AREA;

 public  String PROP_UPDATE_USER;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String loginName;

 private  java.lang.String password;

 private  java.lang.String displayName;

 private  java.lang.String status;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String updateUser;

 private  java.lang.String createUser;

 private  java.lang.String reasonDesc;

 private  java.sql.Date openDate;

 private  java.sql.Date closeDate;

 private  com.gbcom.system.domain.SysPerson person;

 private  com.gbcom.system.domain.SysArea area;

 private  com.gbcom.system.domain.SysCodeDetail userType;

 private  java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles;

 private  java.util.Set<SysUserPrivilege> sysUserPrivileges;

// constructors
public BaseSysUser() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysUser(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysUser(java.lang.Long id, java.lang.String loginName) {
    this.setId(id);
    this.setLoginName(loginName);
    initialize();
}
public java.lang.String getCreateUser(){
    return createUser;
}


public void setPassword(java.lang.String password){
    this.password = password;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public void setReasonDesc(java.lang.String reasonDesc){
    this.reasonDesc = reasonDesc;
}


public void setSysUserPrivileges(java.util.Set<SysUserPrivilege> sysUserPrivileges){
    this.sysUserPrivileges = sysUserPrivileges;
}


public void setArea(com.gbcom.system.domain.SysArea area){
    this.area = area;
}


public java.lang.Long getId(){
    return id;
}


public void setDisplayName(java.lang.String displayName){
    this.displayName = displayName;
}


public java.lang.String getStatus(){
    return status;
}


public java.util.Set<com.gbcom.system.domain.SysUserRole> getSysUserRoles(){
    if (sysUserRoles == null) {
        sysUserRoles = new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>();
    }
    return sysUserRoles;
}


public void setLoginName(java.lang.String loginName){
    this.loginName = loginName;
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


public void setUserType(com.gbcom.system.domain.SysCodeDetail userType){
    this.userType = userType;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void addTosysUserRoles(com.gbcom.system.domain.SysUserRole sysUserRole){
    if (null == getSysUserRoles())
        setSysUserRoles(new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>());
    getSysUserRoles().add(sysUserRole);
}


public java.lang.String getReasonDesc(){
    return reasonDesc;
}


public com.gbcom.system.domain.SysCodeDetail getUserType(){
    return userType;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public void setOpenDate(java.sql.Date openDate){
    this.openDate = openDate;
}


public java.util.Set<SysUserPrivilege> getSysUserPrivileges(){
    if (sysUserPrivileges == null) {
        sysUserPrivileges = new java.util.LinkedHashSet<SysUserPrivilege>();
    }
    return sysUserPrivileges;
}


public java.lang.String getLoginName(){
    return loginName;
}


public void setStatus(java.lang.String status){
    this.status = status;
}


public java.sql.Date getOpenDate(){
    return openDate;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public com.gbcom.system.domain.SysPerson getPerson(){
    return person;
}


public java.lang.String getPassword(){
    return password;
}


public java.sql.Date getCloseDate(){
    return closeDate;
}


public void setCloseDate(java.sql.Date closeDate){
    this.closeDate = closeDate;
}


public void setSysUserRoles(java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles){
    this.sysUserRoles = sysUserRoles;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
}


public java.lang.String getDisplayName(){
    return displayName;
}


public java.lang.String getUpdateUser(){
    return updateUser;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysUser))
        return false;
    else {
        com.gbcom.system.domain.SysUser sysUser = (com.gbcom.system.domain.SysUser) obj;
        if (null == this.getId() || null == sysUser.getId())
            return false;
        else
            return (this.getId().equals(sysUser.getId()));
    }
}


public void setPerson(com.gbcom.system.domain.SysPerson person){
    this.person = person;
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(loginName);
    builder.append(password);
    builder.append(displayName);
    builder.append(status);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(updateUser);
    builder.append(createUser);
    builder.append(reasonDesc);
    builder.append(openDate);
    builder.append(closeDate);
    return builder.toString();
}


public void initialize(){
}


public com.gbcom.system.domain.SysArea getArea(){
    return area;
}


public void addTosysUserPrivileges(SysUserPrivilege sysUserPrivilege){
    if (null == getSysUserPrivileges())
        setSysUserPrivileges(new java.util.LinkedHashSet<SysUserPrivilege>());
    getSysUserPrivileges().add(sysUserPrivilege);
}


}