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
@Table(name = "storage_product_occupy")
public class StorageProductOccupy {

@Id
@GeneratedValue
@Column(name = "occupy_id")
 private  Integer occupyId;

@Column(name = "product_id", nullable = false)
 private  Integer productId;

@Column(name = "order_id", nullable = false)
 private  Integer orderId;

@Column(name = "cur_amt", nullable = false)
 private  Integer curAmt;

@Column(name = "orig_amt", nullable = false)
 private  Integer origAmt;

@Column(name = "status", nullable = false)
 private  String status;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time", updatable = false)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "update_time")
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String display;


public Integer getVersion(){
    return version;
}


public Date getCreateTime(){
    return createTime;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public Integer getOrderId(){
    return orderId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setCurAmt(Integer curAmt){
    this.curAmt = curAmt;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public Integer getProductId(){
    return productId;
}


public Integer getOrigAmt(){
    return origAmt;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getValue(){
    return value;
}


public Integer getCurAmt(){
    return curAmt;
}


public void setOrigAmt(Integer origAmt){
    this.origAmt = origAmt;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public Integer getOccupyId(){
    return occupyId;
}


public String getDisplay(){
    return display;
}


public void setOccupyId(Integer occupyId){
    this.occupyId = occupyId;
}


}