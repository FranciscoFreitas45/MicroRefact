package org.danyuan.application.softm.syslog.po;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
@Entity
@Table(name = "sys_comn_logs")
@DynamicInsert
@DynamicUpdate
public class SysComnLogs implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "uuid", columnDefinition = " varchar(36) COMMENT '主键'")
 private  String uuid;

@Column(name = "url", columnDefinition = " varchar(200) COMMENT '地址'")
 private  String url;

@Column(name = "method", columnDefinition = " varchar(200) COMMENT '请求method'")
 private  String method;

@Column(name = "ip", columnDefinition = " varchar(200) COMMENT 'ip'")
 private  String ip;

@Column(name = "class_method", columnDefinition = " varchar(200) COMMENT '方法名'")
 private  String classMethod;

@Column(name = "class_name", columnDefinition = " varchar(500) COMMENT '类名'")
 private  String className;

@Column(name = "args", columnDefinition = " text COMMENT '请求参数'")
 private  String args;

@Column(name = "request_long", columnDefinition = " int  COMMENT '请求响应时间'")
 private  Long requestLong;

@Column(name = "create_time", updatable = false, columnDefinition = " timestamp default CURRENT_TIMESTAMP  COMMENT '录入时间'")
@CreatedDate
@org.hibernate.annotations.CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "create_user", columnDefinition = " varchar(50) default 'system' COMMENT '录入人员'")
 private  String createUser;

@Column(name = "discription", columnDefinition = " varchar(200) COMMENT '资源功能描述'")
 private  String discription;

@Column(name = "message")
 private  String message;

@Column(name = "param_list", columnDefinition = " varchar(2000)  COMMENT '请求参数'")
 private  String paramList;

@Column(name = "table_name", columnDefinition = " varchar(100)  COMMENT '请求参数'")
 private  String tableName;

@Column(name = "browser", columnDefinition = " varchar(1000)  COMMENT '浏览器'")
 private  String browser;

@Column(name = "browser_type", columnDefinition = " varchar(50)  COMMENT '浏览器名称'")
 private  String browserType;

@Column(name = "os", columnDefinition = " varchar(50)  COMMENT '操作系统'")
 private  String os;

@Column(name = "model", columnDefinition = " varchar(50)  COMMENT '手机型号'")
 private  String model;


public String getCreateUser(){
    return createUser;
}


public void setTableName(String tableName){
    this.tableName = tableName;
}


public String getOs(){
    return os;
}


public Date getCreateTime(){
    return createTime;
}


public String getIp(){
    return ip;
}


public void setBrowserType(String browserType){
    this.browserType = browserType;
}


public String getMethod(){
    return method;
}


public void setClassName(String className){
    this.className = className;
}


public void setArgs(String args){
    this.args = args;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public void setDiscription(String discription){
    this.discription = discription;
}


public void setClassMethod(String classMethod){
    this.classMethod = classMethod;
}


public void setOs(String os){
    this.os = os;
}


public String getModel(){
    return model;
}


public String getTableName(){
    return tableName;
}


public String getMessage(){
    return message;
}


public Long getRequestLong(){
    return requestLong;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public String getClassMethod(){
    return classMethod;
}


public void setRequestLong(Long requestLong){
    this.requestLong = requestLong;
}


public void setMessage(String message){
    this.message = message;
}


public void setIp(String ip){
    this.ip = ip;
}


public String getArgs(){
    return args;
}


public void setUrl(String url){
    this.url = url;
}


public String getBrowserType(){
    return browserType;
}


public String getUrl(){
    return url;
}


public void setMethod(String method){
    this.method = method;
}


public String getDiscription(){
    return discription;
}


public String getParamList(){
    return paramList;
}


public String getUuid(){
    return uuid;
}


public void setModel(String model){
    this.model = model;
}


public String getClassName(){
    return className;
}


public void setParamList(String paramList){
    this.paramList = paramList;
}


public String getBrowser(){
    return browser;
}


}