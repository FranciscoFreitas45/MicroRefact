package com.zammc.domain.reserve;
 import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.sql.Timestamp;
@Entity
@Table(name = "reserve")
@DynamicUpdate
@DynamicInsert
public class ReserveEntity {

 private  long reserveId;

 private  String mobile;

 private  String reserveTime;

 private  int reserveNumbers;

 private  String realName;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  int version;

 private  byte dataStatus;

 private  String summary;


@Id
@Column(name = "reserve_id")
public long getReserveId(){
    return reserveId;
}


public void setReserveId(long reserveId){
    this.reserveId = reserveId;
}


@Basic
@Column(name = "version")
@Version
public int getVersion(){
    return version;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setSummary(String summary){
    this.summary = summary;
}


@Basic
@Column(name = "reserve_numbers")
public int getReserveNumbers(){
    return reserveNumbers;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


@Basic
@Column(name = "summary")
public String getSummary(){
    return summary;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setVersion(int version){
    this.version = version;
}


@Basic
@Column(name = "reserve_time")
public String getReserveTime(){
    return reserveTime;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


public void setReserveNumbers(int reserveNumbers){
    this.reserveNumbers = reserveNumbers;
}


@Basic
@Column(name = "real_name")
public String getRealName(){
    return realName;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


@Basic
@Column(name = "mobile")
public String getMobile(){
    return mobile;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


public void setReserveTime(String reserveTime){
    this.reserveTime = reserveTime;
}


}