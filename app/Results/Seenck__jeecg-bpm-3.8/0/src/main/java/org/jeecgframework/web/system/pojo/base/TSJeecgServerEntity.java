package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "t_s_jeecg_server", schema = "")
@SuppressWarnings("serial")
public class TSJeecgServerEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.util.Date updateDate;

@Excel(name = "IP地址")
 private  java.lang.String ip;

@Excel(name = "mac地址")
 private  java.lang.String mac;

@Excel(name = "启动时间")
 private  java.util.Date startTime;

@Excel(name = "系统名字")
 private  String osName;


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "IP", nullable = true, length = 100)
public java.lang.String getIp(){
    return this.ip;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setIp(java.lang.String ip){
    this.ip = ip;
}


public void setMac(java.lang.String mac){
    this.mac = mac;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "MAC", nullable = true, length = 100)
public java.lang.String getMac(){
    return this.mac;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "START_TIME", nullable = true, length = 32)
public java.util.Date getStartTime(){
    return this.startTime;
}


public void setStartTime(java.util.Date startTime){
    this.startTime = startTime;
}


public void setOsName(String osName){
    this.osName = osName;
}


@Column(name = "OS_NAME", nullable = true, length = 50)
public String getOsName(){
    return osName;
}


}