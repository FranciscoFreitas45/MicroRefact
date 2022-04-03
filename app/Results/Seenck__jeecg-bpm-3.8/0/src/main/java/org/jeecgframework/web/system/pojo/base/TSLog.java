package org.jeecgframework.web.system.pojo.base;
 import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_log")
public class TSLog extends IdEntity{

 private  String userid;

 private  String username;

 private  String realname;

 private  Short loglevel;

 private  Date operatetime;

 private  Short operatetype;

 private  String logcontent;

 private  String broswer;

 private  String note;


@Column(name = "operatetime", nullable = false, length = 35)
public Date getOperatetime(){
    return this.operatetime;
}


public void setUsername(String username){
    this.username = username;
}


@Column(name = "loglevel")
public Short getLoglevel(){
    return this.loglevel;
}


public void setLoglevel(Short loglevel){
    this.loglevel = loglevel;
}


public void setLogcontent(String logcontent){
    this.logcontent = logcontent;
}


@Column(name = "note", length = 300)
public String getNote(){
    return this.note;
}


@Column(name = "username", length = 10)
public String getUsername(){
    return username;
}


public void setOperatetime(Date operatetime){
    this.operatetime = operatetime;
}


@Column(name = "broswer", length = 100)
public String getBroswer(){
    return broswer;
}


public void setRealname(String realname){
    this.realname = realname;
}


@Column(name = "operatetype")
public Short getOperatetype(){
    return this.operatetype;
}


@Column(name = "logcontent", nullable = false, length = 2000)
public String getLogcontent(){
    return this.logcontent;
}


public void setBroswer(String broswer){
    this.broswer = broswer;
}


public void setNote(String note){
    this.note = note;
}


public void setOperatetype(Short operatetype){
    this.operatetype = operatetype;
}


@Column(name = "userid", length = 32)
public String getUserid(){
    return userid;
}


@Column(name = "realname", length = 50)
public String getRealname(){
    return realname;
}


public void setUserid(String userid){
    this.userid = userid;
}


}