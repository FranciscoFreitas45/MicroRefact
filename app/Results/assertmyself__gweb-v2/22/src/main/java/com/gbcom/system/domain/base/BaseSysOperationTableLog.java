package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseSysOperationTableLog implements Serializable{

 public  String REF;

 public  String PROP_CREATE_USER;

 public  String PROP_LOG_XML;

 public  String PROP_CREATE_TIME;

 public  String PROP_UPDATE_TIME;

 public  String PROP_ID;

 public  String PROP_IP_ADDRESS;

 public  String PROP_TABLE;

 public  String PROP_UPDATE_USER;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String logXml;

 private  java.lang.String ipAddress;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String updateUser;

 private  java.lang.String createUser;

 private  com.gbcom.system.domain.ConfigTable table;

// constructors
public BaseSysOperationTableLog() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysOperationTableLog(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public java.lang.String getCreateUser(){
    return createUser;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getIpAddress(){
    return ipAddress;
}


public java.lang.String getLogXml(){
    return logXml;
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


public void setIpAddress(java.lang.String ipAddress){
    this.ipAddress = ipAddress;
}


public void setLogXml(java.lang.String logXml){
    this.logXml = logXml;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
}


public void setUpdateUser(java.lang.String updateUser){
    this.updateUser = updateUser;
}


public com.gbcom.system.domain.ConfigTable getTable(){
    return table;
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


public java.lang.String getUpdateUser(){
    return updateUser;
}


public void setTable(com.gbcom.system.domain.ConfigTable table){
    this.table = table;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysOperationTableLog))
        return false;
    else {
        com.gbcom.system.domain.SysOperationTableLog sysOperationTableLog = (com.gbcom.system.domain.SysOperationTableLog) obj;
        if (null == this.getId() || null == sysOperationTableLog.getId())
            return false;
        else
            return (this.getId().equals(sysOperationTableLog.getId()));
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
    builder.append(logXml);
    builder.append(ipAddress);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(updateUser);
    builder.append(createUser);
    return builder.toString();
}


public void initialize(){
}


}