package com.gbcom.system.domain.base;
 import com.gbcom.system.domain.SysUserPrivilege;
import java.io.Serializable;
public class BaseSysUserPrivilege implements Serializable{

 public  String REF;

 public  String PROP_PRIVILEGE;

 public  String PROP_USER;

 public  String PROP_ID;

 public  String PROP_IS_DENY;

 private  int hashCode;

 private  Long id;

 private  Boolean isDeny;

 private  com.gbcom.system.domain.SysUser user;

 private  com.gbcom.system.domain.SysPrivilege privilege;

// constructors
public BaseSysUserPrivilege() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysUserPrivilege(Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysUserPrivilege(Long id, com.gbcom.system.domain.SysUser user, com.gbcom.system.domain.SysPrivilege privilege) {
    this.setId(id);
    this.setUser(user);
    this.setPrivilege(privilege);
    initialize();
}
public com.gbcom.system.domain.SysPrivilege getPrivilege(){
    return privilege;
}


public void setPrivilege(com.gbcom.system.domain.SysPrivilege privilege){
    this.privilege = privilege;
}


public Boolean getIsDeny(){
    return isDeny;
}


public void setIsDeny(Boolean isDeny){
    this.isDeny = isDeny;
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


public com.gbcom.system.domain.SysUser getUser(){
    return user;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof SysUserPrivilege))
        return false;
    else {
        SysUserPrivilege sysUserPrivilege = (SysUserPrivilege) obj;
        if (null == this.getId() || null == sysUserPrivilege.getId())
            return false;
        else
            return (this.getId().equals(sysUserPrivilege.getId()));
    }
}


public void setId(Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public Long getId(){
    return id;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(isDeny);
    return builder.toString();
}


public void initialize(){
}


public void setUser(com.gbcom.system.domain.SysUser user){
    this.user = user;
}


}