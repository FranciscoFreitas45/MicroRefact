package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysUserRole implements Serializable{

 public  String REF;

 public  String PROP_USER;

 public  String PROP_ROLE;

 public  String PROP_ID;

 private  int hashCode;

 private  Long id;

 private  com.gbcom.system.domain.SysRole role;

 private  com.gbcom.system.domain.SysUser user;

// constructors
public BaseSysUserRole() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysUserRole(Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysUserRole(Long id, com.gbcom.system.domain.SysRole role, com.gbcom.system.domain.SysUser user) {
    this.setId(id);
    this.setRole(role);
    this.setUser(user);
    initialize();
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


public void setRole(com.gbcom.system.domain.SysRole role){
    this.role = role;
}


public com.gbcom.system.domain.SysUser getUser(){
    return user;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysUserRole))
        return false;
    else {
        com.gbcom.system.domain.SysUserRole sysUserRole = (com.gbcom.system.domain.SysUserRole) obj;
        if (null == this.getId() || null == sysUserRole.getId())
            return false;
        else
            return (this.getId().equals(sysUserRole.getId()));
    }
}


public com.gbcom.system.domain.SysRole getRole(){
    return role;
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
    return builder.toString();
}


public void initialize(){
}


public void setUser(com.gbcom.system.domain.SysUser user){
    this.user = user;
}


}