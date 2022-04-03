package com.zis.trade.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang3.StringUtils;
@Entity
@Table(name = "order_detail")
public class OrderDetail {

@Id
@GeneratedValue
@Column(name = "order_detail_id", nullable = false)
 private  Integer orderDetailId;

@Column(name = "item_id")
 private  Integer itemId;

@Column(name = "item_out_num")
 private  String itemOutNum;

@Column(name = "sku_id")
 private  Integer skuId;

@Column(name = "item_name")
 private  String itemName;

@Column(name = "item_count")
 private  Integer itemCount;

@Column(name = "item_price")
 private  Double itemPrice;

@Column(name = "status")
 private  String status;

@Column(name = "create_time", updatable = false, length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time", length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

@ManyToOne
@JoinColumn(name = "order_id")
 private  Order order;

 private  String value;

 private  String display;

// Constructors
/**
 * default constructor
 */
public OrderDetail() {
}
public Date getCreateTime(){
    return this.createTime;
}


public Integer getSkuId(){
    return this.skuId;
}


public Integer getItemCount(){
    return this.itemCount;
}


public DetailStatus getEnum(String value){
    for (DetailStatus record : values()) {
        if (record.getValue().equals(value)) {
            return record;
        }
    }
    return null;
}


public String getStatus(){
    return this.status;
}


public Integer getItemId(){
    return this.itemId;
}


public void setItemOutNum(String itemOutNum){
    this.itemOutNum = itemOutNum;
}


public void setItemId(Integer itemId){
    this.itemId = itemId;
}


public Integer getVersion(){
    return this.version;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setStatus(String status){
    this.status = status;
}


public void setItemCount(Integer itemCount){
    this.itemCount = itemCount;
}


public Date getUpdateTime(){
    return this.updateTime;
}


public void setOrder(Order order){
    this.order = order;
}


public String getValue(){
    return value;
}


public void setOrderDetailId(Integer orderDetailId){
    this.orderDetailId = orderDetailId;
}


public String getItemName(){
    return this.itemName;
}


public String getItemOutNum(){
    return itemOutNum;
}


public Integer getOrderDetailId(){
    return this.orderDetailId;
}


public Order getOrder(){
    return order;
}


public boolean isDefined(String value){
    if (StringUtils.isBlank(value)) {
        return false;
    }
    return getEnum(value) != null;
}


public Double getItemPrice(){
    return this.itemPrice;
}


public void setItemPrice(Double itemPrice){
    this.itemPrice = itemPrice;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public String getDisplay(){
    return display;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}