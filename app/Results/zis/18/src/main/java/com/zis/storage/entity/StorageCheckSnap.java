package com.zis.storage.entity;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "storage_check_snap")
public class StorageCheckSnap implements Comparable<StorageCheckSnap>{

@Id
@GeneratedValue
@Column(name = "check_snap_id")
 private  Integer checkSnapId;

@Column(name = "check_id")
 private  Integer checkId;

@Column(name = "sku_id")
 private  Integer skuId;

@Column(name = "snap_num")
 private  Integer snapNum;

@Column(name = "check_time", length = 128)
 private  String checkTime;

@Column(name = "isdelete")
 private  Boolean isdelete;

@Column(name = "gmt_create", length = 32, updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "gmt_modify", length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public StorageCheckSnap() {
}/**
 * full constructor
 */
public StorageCheckSnap(Integer checkId, Integer skuId, Integer snapNum, String checkTime, Boolean isdelete, Date gmtCreate, Date gmtModify, Integer version) {
    this.checkId = checkId;
    this.skuId = skuId;
    this.snapNum = snapNum;
    this.checkTime = checkTime;
    this.isdelete = isdelete;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public Integer getVersion(){
    return this.version;
}


public Integer getCheckId(){
    return this.checkId;
}


public Boolean getIsdelete(){
    return this.isdelete;
}


public void setCheckSnapId(Integer checkSnapId){
    this.checkSnapId = checkSnapId;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public void setVersion(Integer version){
    this.version = version;
}


public Integer getSkuId(){
    return this.skuId;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public void main(String[] args){
    StorageCheckSnap snap = new StorageCheckSnap();
    snap.setSkuId(10002);
    snap.setSnapNum(10);
    StorageCheckSnap snap2 = new StorageCheckSnap();
    snap2.setSkuId(10002);
    snap2.setSnapNum(10);
    System.out.println(snap.equals(snap2));
    List<StorageCheckSnap> list = new ArrayList<StorageCheckSnap>();
    list.add(snap2);
    list.add(snap);
    for (StorageCheckSnap storageCheckSnap : list) {
        System.out.println(storageCheckSnap.getSkuId());
    }
    Collections.sort(list);
    for (StorageCheckSnap storageCheckSnap : list) {
        System.out.println(storageCheckSnap.getSkuId());
    }
}


public Date getGmtModify(){
    return this.gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public int compareTo(StorageCheckSnap obj){
    if (this.getSkuId() > obj.getSkuId()) {
        return 1;
    }
    if (this.getSkuId() < obj.getSkuId()) {
        return -1;
    }
    return 0;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public String getCheckTime(){
    return this.checkTime;
}


public void setCheckTime(String checkTime){
    this.checkTime = checkTime;
}


public void setSnapNum(Integer snapNum){
    this.snapNum = snapNum;
}


public void setIsdelete(Boolean isdelete){
    this.isdelete = isdelete;
}


public boolean equals(StorageCheckSnap obj){
    if (this.getSkuId().equals(obj.getSkuId()) && this.getSnapNum().equals(obj.getSnapNum())) {
        return true;
    }
    return false;
}


public void setCheckId(Integer checkId){
    this.checkId = checkId;
}


public Integer getCheckSnapId(){
    return this.checkSnapId;
}


public Integer getSnapNum(){
    return this.snapNum;
}


}