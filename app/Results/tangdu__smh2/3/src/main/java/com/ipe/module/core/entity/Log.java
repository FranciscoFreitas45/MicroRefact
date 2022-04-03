package com.ipe.module.core.entity;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
import java.util.Date;
@JsonAutoDetect
@Entity
@Table(name = "t_sys_log", schema = "", catalog = "db_work")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Log extends IDEntity{

 private  long serialVersionUID;

 private  String accessMethod;

 private  String accessPerson;

 private  String logType;

 private  String accessIp;

 private  String operate;

 private  String remark;

 private  Date accessTime;

 private  Date leaveTime;

 private  String accessUserid;


public void setLogType(String logType){
    this.logType = logType;
}


public void setAccessTime(Date accessTime){
    this.accessTime = accessTime;
}


@Column(name = "access_method")
public String getAccessMethod(){
    return accessMethod;
}


@Column(name = "log_type")
public String getLogType(){
    return logType;
}


public void setAccessPerson(String accessPerson){
    this.accessPerson = accessPerson;
}


public void setLeaveTime(Date leaveTime){
    this.leaveTime = leaveTime;
}


public void setAccessMethod(String accessMethod){
    this.accessMethod = accessMethod;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "access_time")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getAccessTime(){
    return accessTime;
}


public void setAccessUserid(String accessUserid){
    this.accessUserid = accessUserid;
}


@Column(name = "operate_")
public String getOperate(){
    return operate;
}


@Column(name = "access_ip")
public String getAccessIp(){
    return accessIp;
}


public void setAccessIp(String accessIp){
    this.accessIp = accessIp;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setOperate(String operate){
    this.operate = operate;
}


@Column(name = "remark_")
public String getRemark(){
    return remark;
}


@Column(name = "access_person")
public String getAccessPerson(){
    return accessPerson;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "leave_time")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getLeaveTime(){
    return leaveTime;
}


@Column(name = "access_userid")
public String getAccessUserid(){
    return accessUserid;
}


}