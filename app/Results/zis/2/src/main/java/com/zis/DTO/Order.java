package com.zis.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang3.StringUtils;
public class Order {

 private  Integer id;

 private  Integer shopId;

 private  String shopName;

 private  String pName;

 private  Integer companyId;

 private  Integer repoId;

 private  String receiverName;

 private  String receiverPhone;

 private  String receiverAddr;

 private  String orderGroupNumber;

 private  String expressCompany;

 private  String expressNumber;

 private  Double orderMoney;

 private  String orderType;

 private  String storageStatus;

 private  String expressStatus;

 private  String payStatus;

 private  boolean blockFlag;

 private  String blockReason;

 private  Date refundApplyTime;

 private  Date refundFinishTime;

 private  Date payTime;

 private  Date arrangeTime;

 private  Date expressTime;

 private  String salerRemark;

 private  String buyerMessage;

 private  Date createTime;

 private  Date updateTime;

 private  Integer version;

 private  List<OrderDetail> orderDetails;

 private  String value;

 private  String display;

 private  String value;

 private  String display;

 private  String value;

 private  String display;

 private  String value;

 private  String display;

// Constructors
/**
 * default constructor
 */
public Order() {
}
public Date getPayTime(){
    return this.payTime;
}


public String getReceiverPhone(){
    return this.receiverPhone;
}


public Date getCreateTime(){
    return this.createTime;
}


public boolean getBlockFlag(){
    return blockFlag;
}


public Date getArrangeTime(){
    return this.arrangeTime;
}


public OrderType getEnum(String value){
    for (OrderType record : values()) {
        if (record.getValue().equals(value)) {
            return record;
        }
    }
    return null;
}


public String getExpressStatus(){
    return expressStatus;
}


public String getOrderGroupNumber(){
    return this.orderGroupNumber;
}


public String getReceiverAddr(){
    return this.receiverAddr;
}


public Date getRefundApplyTime(){
    return refundApplyTime;
}


public String getpName(){
    return pName;
}


public String getStorageStatus(){
    return this.storageStatus;
}


public String getPayStatus(){
    return this.payStatus;
}


public Date getUpdateTime(){
    return this.updateTime;
}


public String getExpressCompany(){
    return expressCompany;
}


public Date getRefundFinishTime(){
    return refundFinishTime;
}


public Integer getRepoId(){
    return this.repoId;
}


public String getExpressNumber(){
    return expressNumber;
}


public Integer getId(){
    return id;
}


public List<OrderDetail> getOrderDetails(){
    return orderDetails;
}


public String getBuyerMessage(){
    return this.buyerMessage;
}


public String getShopName(){
    return shopName;
}


public Double getOrderMoney(){
    return orderMoney;
}


public Integer getVersion(){
    return this.version;
}


public Date getExpressTime(){
    return expressTime;
}


public String getOrderType(){
    return this.orderType;
}


public Integer getShopId(){
    return shopId;
}


public String getSalerRemark(){
    return this.salerRemark;
}


public String getValue(){
    return value;
}


public Integer getCompanyId(){
    return this.companyId;
}


public String getReceiverName(){
    return this.receiverName;
}


public String getDisplay(){
    return display;
}


public String getBlockReason(){
    return blockReason;
}


}