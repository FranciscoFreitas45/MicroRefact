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
@Table(name = "storage_position")
public class StoragePosition {

@Id
@GeneratedValue
@Column(name = "pos_id")
 private  Integer posId;

@Column(name = "repo_id", nullable = false, updatable = false)
 private  Integer repoId;

@Column(name = "label", nullable = false)
 private  String label;

@Column(name = "pos_status")
 private  String posStatus;

@Column(name = "lock_reason")
 private  String lockReason;

@Column(name = "gmt_create", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String display;


public Integer getRepoId(){
    return repoId;
}


public Integer getVersion(){
    return this.version;
}


public String getLabel(){
    return label;
}


public Integer getPosId(){
    return posId;
}


public void setVersion(Integer version){
    this.version = version;
}


public String getLockReason(){
    return this.lockReason;
}


public void setPosId(Integer posId){
    this.posId = posId;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public String getPosStatus(){
    return posStatus;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getValue(){
    return value;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public void setLockReason(String lockReason){
    this.lockReason = lockReason;
}


public void setPosStatus(String posStatus){
    this.posStatus = posStatus;
}


public void setLabel(String label){
    this.label = label;
}


public String getDisplay(){
    return display;
}


}