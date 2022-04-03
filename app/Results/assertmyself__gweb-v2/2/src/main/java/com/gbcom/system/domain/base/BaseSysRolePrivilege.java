package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysRolePrivilege implements Serializable{

 public  String REF;

 public  String PROP_PRIVILEGE;

 public  String PROP_ROLE;

 public  String PROP_ID;

 private  int hashCode;

 private  java.lang.Long id;

 private  com.gbcom.system.domain.SysPrivilege privilege;

 private  com.gbcom.system.domain.SysRole role;

// constructors
public BaseSysRolePrivilege() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysRolePrivilege(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysRolePrivilege(java.lang.Long id, com.gbcom.system.domain.SysPrivilege privilege, com.gbcom.system.domain.SysRole role) {
    this.setId(id);
    this.setPrivilege(privilege);
    this.setRole(role);
    initialize();
}
public com.gbcom.system.domain.SysPrivilege getPrivilege(){
    return privilege;
}


public void setPrivilege(com.gbcom.system.domain.SysPrivilege privilege){
    this.privilege = privilege;
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


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysRolePrivilege))
        return false;
    else {
        com.gbcom.system.domain.SysRolePrivilege sysRolePrivilege = (com.gbcom.system.domain.SysRolePrivilege) obj;
        if (null == this.getId() || null == sysRolePrivilege.getId())
            return false;
        else
            return (this.getId().equals(sysRolePrivilege.getId()));
    }
}


public com.gbcom.system.domain.SysRole getRole(){
    return role;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public java.lang.Long getId(){
    return id;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    return builder.toString();
}


public void initialize(){
}


}