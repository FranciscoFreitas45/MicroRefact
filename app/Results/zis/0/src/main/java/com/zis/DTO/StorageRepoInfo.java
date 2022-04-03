package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class StorageRepoInfo {

 private  Integer repoId;

 private  String name;

 private  Integer ownerId;

 private  String status;

 private  Date gmtCreate;

 private  Date gmtModify;

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


public Integer getVersion(){
    return version;
}


public String getName(){
    return name;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setOwnerId(Integer ownerId){
    this.ownerId = ownerId;
}


public String getStatus(){
    return status;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getValue(){
    return value;
}


public Integer getOwnerId(){
    return ownerId;
}


public String getDisplay(){
    return display;
}


}