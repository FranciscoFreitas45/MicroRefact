package com.gbcom.system.domain.base;
 import java.io.Serializable;
import com.gbcom.system.aop.UserLog;
import com.gbcom.Interface.SysUser;
public class BaseSysLog implements Serializable{

 public  String REF;

 public  String PROP_USER;

 public  String PROP_IE_VERSION;

 public  String PROP_PAGE_URL;

 public  String PROP_OUT_TIME;

 public  String PROP_SESSIONID;

 public  String PROP_ID;

 public  String PROP_LOG_TYPE;

 public  String PROP_IP_ADDRESS;

 public  String PROP_ENTER_TIME;

 private  int hashCode;

 private  java.lang.Long id;

 private  java.lang.String ipAddress;

 private  java.sql.Timestamp enterTime;

 private  java.sql.Timestamp outTime;

 private  java.lang.String pageUrl;

 private  java.lang.String ieVersion;

 private  java.lang.String sessionid;

 private  java.lang.String result;

 private  java.lang.String moudle;

 private  java.lang.String eventType;

 private  java.lang.String message;

 private  java.lang.String logType;

 private  com.gbcom.system.domain.SysUser user;

// constructors
public BaseSysLog() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseSysLog(java.lang.Long id) {
    this.setId(id);
    initialize();
}
public void setLogType(java.lang.String logType){
    this.logType = logType;
}


public java.lang.String getLogType(){
    return logType;
}


public java.lang.String getIpAddress(){
    return ipAddress;
}


public void setResult(java.lang.String result){
    this.result = result;
}


public java.sql.Timestamp getEnterTime(){
    return enterTime;
}


public void setPageUrl(java.lang.String pageUrl){
    this.pageUrl = pageUrl;
}


public com.gbcom.system.domain.SysUser getUser(){
    return user;
}


public java.lang.Long getId(){
    return id;
}


public java.lang.String getMoudle(){
    return moudle;
}


public java.lang.String getPageUrl(){
    return pageUrl;
}


public void setIeVersion(java.lang.String ieVersion){
    this.ieVersion = ieVersion;
}


public void setEventType(java.lang.String eventType){
    this.eventType = eventType;
}


public void setSessionid(java.lang.String sessionid){
    this.sessionid = sessionid;
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


public java.sql.Timestamp getOutTime(){
    return outTime;
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public java.lang.String getSessionid(){
    return sessionid;
}


public void setUser(com.gbcom.system.domain.SysUser user){
    this.user = user;
}


public void setEnterTime(java.sql.Timestamp enterTime){
    this.enterTime = enterTime;
}


public java.lang.String getIeVersion(){
    return ieVersion;
}


public java.lang.String getMessage(){
    return message;
}


public void setMessage(java.lang.String message){
    this.message = message;
}


public void setIpAddress(java.lang.String ipAddress){
    this.ipAddress = ipAddress;
}


public void setMoudle(java.lang.String moudle){
    this.moudle = moudle;
}


public java.lang.String getEventType(){
    return eventType;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.SysLog))
        return false;
    else {
        com.gbcom.system.domain.SysLog sysLog = (com.gbcom.system.domain.SysLog) obj;
        if (null == this.getId() || null == sysLog.getId())
            return false;
        else
            return (this.getId().equals(sysLog.getId()));
    }
}


public java.lang.String getResult(){
    return result;
}


public void setOutTime(java.sql.Timestamp outTime){
    this.outTime = outTime;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(ipAddress);
    builder.append(enterTime);
    builder.append(outTime);
    builder.append(pageUrl);
    builder.append(ieVersion);
    builder.append(sessionid);
    return builder.toString();
}


public void initialize(){
}


}