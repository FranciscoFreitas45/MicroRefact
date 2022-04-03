package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysPersonDept implements Serializable{

 public  String REF;

 public  String PROP_ORDER_NO;

 public  String PROP_DEPT;

 public  String PROP_IS_VALID;

 public  String PROP_POSITION;

 public  String PROP_ID;

 public  String PROP_PERSON;

 public  String PROP_IS_MANAGER;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String position;

 private  java.lang.Long orderNo;

 private  java.lang.Boolean isValid;

 private  java.lang.Boolean isManager;

 private  com.gbcom.system.domain.SysDept dept;

 private  com.gbcom.system.domain.SysPerson person;

// constructors
public BaseSysPersonDept() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysPersonDept(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseSysPersonDept(java.lang.Long id, com.gbcom.system.domain.SysDept dept, com.gbcom.system.domain.SysPerson person) {
    this.setId(id);
    this.setDept(dept);
    this.setPerson(person);
    initialize();
}
public java.lang.Long getOrderNo(){
    return orderNo;
}


public void setIsValid(java.lang.Boolean isValid){
    this.isValid = isValid;
}


public com.gbcom.system.domain.SysDept getDept(){
    return dept;
}


public void setOrderNo(java.lang.Long orderNo){
    this.orderNo = orderNo;
}


public java.lang.Long getId(){
    return id;
}


public java.lang.Boolean getIsManager(){
    return isManager;
}


public void setPosition(java.lang.String position){
    this.position = position;
}


public void setDept(com.gbcom.system.domain.SysDept dept){
    this.dept = dept;
}


public com.gbcom.system.domain.SysPerson getPerson(){
    return person;
}


public java.lang.String getPosition(){
    return position;
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


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysPersonDept))
        return false;
    else {
        com.gbcom.system.domain.SysPersonDept sysPersonDept = (com.gbcom.system.domain.SysPersonDept) obj;
        if (null == this.getId() || null == sysPersonDept.getId())
            return false;
        else
            return (this.getId().equals(sysPersonDept.getId()));
    }
}


public void setPerson(com.gbcom.system.domain.SysPerson person){
    this.person = person;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setIsManager(java.lang.Boolean isManager){
    this.isManager = isManager;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(position);
    builder.append(orderNo);
    builder.append(isValid);
    builder.append(isManager);
    return builder.toString();
}


public void initialize(){
}


public java.lang.Boolean getIsValid(){
    return isValid;
}


}