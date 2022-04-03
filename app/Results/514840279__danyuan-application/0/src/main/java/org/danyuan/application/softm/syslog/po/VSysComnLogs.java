package org.danyuan.application.softm.syslog.po;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class VSysComnLogs {

@Id
@Column(name = "id", columnDefinition = " varchar(36) COMMENT '主键'")
 private  String id;

@Column(name = "ip", columnDefinition = " varchar(200) COMMENT 'ip'")
 private  String ip;

@Column(name = "create_user", columnDefinition = " varchar(50) default 'system' COMMENT '录入人员'")
 private  String createUser;

@Column(name = "param_list", columnDefinition = " varchar(2000)  COMMENT '请求参数'")
 private  String paramList;

@Column(name = "DATE1", columnDefinition = " varchar(50) default 'system' COMMENT '录入人员'")
 private  String date1;

@Column(name = "TIME1", columnDefinition = " varchar(50) default 'system' COMMENT '录入人员'")
 private  String time1;

@Column(name = "num", columnDefinition = " varchar(50) default 'system' COMMENT '录入人员'")
 private  Long num;


public String getCreateUser(){
    return createUser;
}


public String getDate1(){
    return date1;
}


public String getIp(){
    return ip;
}


public void setNum(Long num){
    this.num = num;
}


public String getId(){
    return id;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
}


public void setIp(String ip){
    this.ip = ip;
}


public String getParamList(){
    return paramList;
}


public Long getNum(){
    return num;
}


public void setId(String id){
    this.id = id;
}


public void setDate1(String date1){
    this.date1 = date1;
}


public void setParamList(String paramList){
    this.paramList = paramList;
}


public void setTime1(String time1){
    this.time1 = time1;
}


public String getTime1(){
    return time1;
}


}