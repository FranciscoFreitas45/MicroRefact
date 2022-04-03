package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysParameter implements Serializable{

 public  String REF;

 public  String PROP_NAME;

 public  String PROP_CREATE_USER;

 public  String PROP_VALUE;

 public  String PROP_CREATE_TIME;

 public  String PROP_CONSTRAINT;

 public  String PROP_UPDATE_TIME;

 public  String PROP_ID;

 public  String PROP_CLOBVALUE;

 public  String PROP_CODE;

 public  String PROP_UPDATE_USER;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String value;

 private  java.lang.String constraint;

 private  java.lang.String clobvalue;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String createUser;

 private  java.lang.String updateUser;

// constructors
public BaseSysParameter() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysParameter(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setName(java.lang.String name){
    this.name = name;
}


public java.lang.String getCreateUser(){
    return createUser;
}


public java.lang.String getClobvalue(){
    return clobvalue;
}


public void setClobvalue(java.lang.String clobvalue){
    this.clobvalue = clobvalue;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getName(){
    return name;
}


public void setCode(java.lang.String code){
    this.code = code;
}


public java.lang.Long getId(){
    return id;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public java.lang.String getValue(){
    return value;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public void setConstraint(java.lang.String constraint){
    this.constraint = constraint;
}


public java.lang.String getConstraint(){
    return constraint;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
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


public void setValue(java.lang.String value){
    this.value = value;
}


public java.lang.String getUpdateUser(){
    return updateUser;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysParameter))
        return false;
    else {
        com.gbcom.system.domain.SysParameter sysParameter = (com.gbcom.system.domain.SysParameter) obj;
        if (null == this.getId() || null == sysParameter.getId())
            return false;
        else
            return (this.getId().equals(sysParameter.getId()));
    }
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setUpdateTime(java.sql.Timestamp updateTime){
    this.updateTime = updateTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(code);
    builder.append(name);
    builder.append(value);
    builder.append(constraint);
    builder.append(clobvalue);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(createUser);
    builder.append(updateUser);
    return builder.toString();
}


public void initialize(){
}


public java.lang.String getCode(){
    return code;
}


}