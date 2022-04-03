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
@Table(name = "storage_repo_info")
public class StorageRepoInfo {

@Id
@GeneratedValue
@Column(name = "repo_id")
 private  Integer repoId;

@Column(name = "name")
 private  String name;

@Column(name = "owner_id")
 private  Integer ownerId;

@Column(name = "status")
 private  String status;

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

// Constructors
/**
 * default constructor
 */
public StorageRepoInfo() {
}
public Integer getRepoId(){
    return repoId;
}


public void setName(String name){
    this.name = name;
}


public Integer getVersion(){
    return version;
}


public String getName(){
    return name;
}


public void setVersion(Integer version){
    this.version = version;
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


public void setOwnerId(Integer ownerId){
    this.ownerId = ownerId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
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


public Integer getOwnerId(){
    return ownerId;
}


public String getDisplay(){
    return display;
}


}