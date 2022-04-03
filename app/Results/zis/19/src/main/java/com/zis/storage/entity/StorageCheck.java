package com.zis.storage.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "storage_check")
public class StorageCheck {

@Id
@GeneratedValue
@Column(name = "check_id", nullable = false)
 private  Integer checkId;

@Column(name = "user_id")
 private  Integer userId;

@Column(name = "plan_id")
 private  Integer planId;

@Column(name = "position_id", nullable = false)
 private  Integer positionId;

@Column(name = "check_result", length = 512)
 private  String checkResult;

@Column(name = "gmt_create", updatable = false, length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify", length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

@Column(name = "memo", length = 512)
 private  String memo;

public StorageCheck() {
}public StorageCheck(Integer userId, Integer planId, Integer positionId, String checkResult, Date gmtCreate, Date gmtModify, Integer version, String memo) {
    this.userId = userId;
    this.planId = planId;
    this.positionId = positionId;
    this.checkResult = checkResult;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
    this.memo = memo;
}
public Integer getVersion(){
    return this.version;
}


public Integer getCheckId(){
    return this.checkId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setCheckResult(String checkResult){
    this.checkResult = checkResult;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getPositionId(){
    return this.positionId;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public void setPlanId(Integer planId){
    this.planId = planId;
}


public Integer getPlanId(){
    return this.planId;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setPositionId(Integer positionId){
    this.positionId = positionId;
}


public String getCheckResult(){
    return this.checkResult;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public void setCheckId(Integer checkId){
    this.checkId = checkId;
}


public Integer getUserId(){
    return this.userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}