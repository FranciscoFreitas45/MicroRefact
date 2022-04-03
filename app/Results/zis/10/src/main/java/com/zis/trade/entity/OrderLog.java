package com.zis.trade.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang3.StringUtils;
@Entity
@Table(name = "order_log")
public class OrderLog {

@Id
@GeneratedValue
@Column(name = "order_log_id", nullable = false)
 private  Integer orderLogId;

@Column(name = "order_id")
 private  Integer orderId;

@Column(name = "order_group_number")
 private  String orderGroupNumber;

@Column(name = "operater_id")
 private  Integer operaterId;

@Column(name = "operate_detail")
 private  String operateDetail;

@Column(name = "operate_type")
 private  String operateType;

@Column(name = "create_time", updatable = false, length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time", length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

 private  String value;

 private  String display;

// Constructors
/**
 * default constructor
 */
public OrderLog() {
}/**
 * full constructor
 */
public OrderLog(Integer orderId, String orderGroupNumber, Integer operaterId, String operateDetail, String operateType, Date createTime, Date updateTime, Integer version) {
    this.orderId = orderId;
    this.orderGroupNumber = orderGroupNumber;
    this.operaterId = operaterId;
    this.operateDetail = operateDetail;
    this.operateType = operateType;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
}
public Integer getVersion(){
    return this.version;
}


public Integer getOperaterId(){
    return this.operaterId;
}


public Date getCreateTime(){
    return this.createTime;
}


public String getOperateType(){
    return this.operateType;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setOperaterId(Integer operaterId){
    this.operaterId = operaterId;
}


public Integer getOrderId(){
    return this.orderId;
}


public OperateType getEnum(String value){
    for (OperateType record : values()) {
        if (record.getValue().equals(value)) {
            return record;
        }
    }
    return null;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


public String getOperateDetail(){
    return this.operateDetail;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public Integer getOrderLogId(){
    return this.orderLogId;
}


public Date getUpdateTime(){
    return this.updateTime;
}


public String getValue(){
    return value;
}


public void setOrderGroupNumber(String orderGroupNumber){
    this.orderGroupNumber = orderGroupNumber;
}


public void setOperateDetail(String operateDetail){
    this.operateDetail = operateDetail;
}


public boolean isDefined(String value){
    if (StringUtils.isBlank(value)) {
        return false;
    }
    return getEnum(value) != null;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public String getOrderGroupNumber(){
    return this.orderGroupNumber;
}


public String getDisplay(){
    return display;
}


public void setOrderLogId(Integer orderLogId){
    this.orderLogId = orderLogId;
}


}