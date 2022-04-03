package com.gbcom.system.domain.base;
 import com.hc.core.orm.log.TableLogConfig;
import java.io.Serializable;
public class BaseConfigTable implements Serializable,TableLogConfig{

 public  String REF;

 public  String PROP_IS_LOG;

 public  String PROP_CREATE_USER;

 public  String PROP_CREATE_TIME;

 public  String PROP_UPDATE_TIME;

 public  String PROP_ID;

 public  String PROP_TABLE_NAME;

 public  String PROP_UPDATE_USER;

 public  String PROP_EXTEND_XML;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String tableName;

 private  java.lang.Boolean isLog;

 private  java.lang.String extendXml;

 private  java.sql.Timestamp createTime;

 private  java.sql.Timestamp updateTime;

 private  java.lang.String updateUser;

 private  java.lang.String createUser;

 private  java.util.Set<com.gbcom.system.domain.SysOperationTableLog> sysOperationTableLogs;

// constructors
public BaseConfigTable() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseConfigTable(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


public java.lang.String getCreateUser(){
    return createUser;
}


public java.sql.Timestamp getCreateTime(){
    return createTime;
}


public java.lang.String getExtendXml(){
    return extendXml;
}


public java.lang.String getTableName(){
    return tableName;
}


public java.lang.Long getId(){
    return id;
}


public void setCreateTime(java.sql.Timestamp createTime){
    this.createTime = createTime;
}


public java.util.Set<com.gbcom.system.domain.SysOperationTableLog> getSysOperationTableLogs(){
    if (sysOperationTableLogs == null) {
        sysOperationTableLogs = new java.util.LinkedHashSet<com.gbcom.system.domain.SysOperationTableLog>();
    }
    return sysOperationTableLogs;
}


public void setCreateUser(java.lang.String createUser){
    this.createUser = createUser;
}


public void setExtendXml(java.lang.String extendXml){
    this.extendXml = extendXml;
}


public void setIsLog(java.lang.Boolean isLog){
    this.isLog = isLog;
}


public java.sql.Timestamp getUpdateTime(){
    return updateTime;
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


public java.lang.String getUpdateUser(){
    return updateUser;
}


public void addTosysOperationTableLogs(com.gbcom.system.domain.SysOperationTableLog sysOperationTableLog){
    if (null == getSysOperationTableLogs())
        setSysOperationTableLogs(new java.util.LinkedHashSet<com.gbcom.system.domain.SysOperationTableLog>());
    getSysOperationTableLogs().add(sysOperationTableLog);
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.ConfigTable))
        return false;
    else {
        com.gbcom.system.domain.ConfigTable configTable = (com.gbcom.system.domain.ConfigTable) obj;
        if (null == this.getId() || null == configTable.getId())
            return false;
        else
            return (this.getId().equals(configTable.getId()));
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
    builder.append(tableName);
    builder.append(isLog);
    builder.append(extendXml);
    builder.append(createTime);
    builder.append(updateTime);
    builder.append(updateUser);
    builder.append(createUser);
    return builder.toString();
}


public void initialize(){
}


public void setSysOperationTableLogs(java.util.Set<com.gbcom.system.domain.SysOperationTableLog> sysOperationTableLogs){
    this.sysOperationTableLogs = sysOperationTableLogs;
}


public java.lang.Boolean getIsLog(){
    return isLog;
}


}