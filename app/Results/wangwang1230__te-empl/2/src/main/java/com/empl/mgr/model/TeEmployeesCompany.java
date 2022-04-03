package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "te_employees_company")
public class TeEmployeesCompany {

 private  long serialVersionUID;

 private  long comId;

 private  Date timestamp;

 private  long emplNo;

 private  String comName;

 private  String comParticipateTime;

 private  String comLeaveTime;

 private  String comPosition;

 private  String comReason;

/**
 * default constructor
 */
public TeEmployeesCompany() {
}/**
 * full constructor
 */
public TeEmployeesCompany(long emplNo, String comName, String comParticipateTime, String comLeaveTime, String comPosition, String comReason) {
    this.emplNo = emplNo;
    this.comName = comName;
    this.comParticipateTime = comParticipateTime;
    this.comLeaveTime = comLeaveTime;
    this.comPosition = comPosition;
    this.comReason = comReason;
}
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "comId", unique = true, nullable = false)
public long getComId(){
    return this.comId;
}


public void setComId(long comId){
    this.comId = comId;
}


@Column(name = "comParticipateTime", length = 12)
public String getComParticipateTime(){
    return this.comParticipateTime;
}


public void setComReason(String comReason){
    this.comReason = comReason;
}


public void setComLeaveTime(String comLeaveTime){
    this.comLeaveTime = comLeaveTime;
}


@Column(name = "comLeaveTime", length = 12)
public String getComLeaveTime(){
    return this.comLeaveTime;
}


@Column(name = "comName", length = 256)
public String getComName(){
    return this.comName;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "comReason", length = 1024)
public String getComReason(){
    return this.comReason;
}


@Override
public String toString(){
    return "TeEmployeesCompany [comId:" + comId + ", timestamp:" + timestamp + ", emplNo:" + emplNo + ", comName:" + comName + ", comParticipateTime:" + comParticipateTime + ", comLeaveTime:" + comLeaveTime + ", comPosition:" + comPosition + ", comReason:" + comReason + "]";
}


public void setComParticipateTime(String comParticipateTime){
    this.comParticipateTime = comParticipateTime;
}


@Column(name = "comPosition", length = 128)
public String getComPosition(){
    return this.comPosition;
}


public void setComName(String comName){
    this.comName = comName;
}


public void setEmplNo(long emplNo){
    this.emplNo = emplNo;
}


public void setComPosition(String comPosition){
    this.comPosition = comPosition;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


@Column(name = "emplNo")
public long getEmplNo(){
    return this.emplNo;
}


}