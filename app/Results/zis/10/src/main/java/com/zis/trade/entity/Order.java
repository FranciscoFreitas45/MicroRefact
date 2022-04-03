package com.zis.trade.entity;
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
@Entity
@Table(name = "`order`")
public class Order {

@Id
@GeneratedValue
@Column(name = "id", nullable = false)
 private  Integer id;

@Column(name = "shop_id")
 private  Integer shopId;

@Column(name = "shop_name")
 private  String shopName;

@Column(name = "p_name")
 private  String pName;

@Column(name = "company_id")
 private  Integer companyId;

@Column(name = "repo_id")
 private  Integer repoId;

@Column(name = "receiver_name")
 private  String receiverName;

@Column(name = "receiver_phone")
 private  String receiverPhone;

@Column(name = "receiver_addr")
 private  String receiverAddr;

@Column(name = "order_group_number")
 private  String orderGroupNumber;

@Column(name = "express_company")
 private  String expressCompany;

@Column(name = "express_number")
 private  String expressNumber;

@Column(name = "order_money")
 private  Double orderMoney;

@Column(name = "order_type")
 private  String orderType;

@Column(name = "storage_status")
 private  String storageStatus;

@Column(name = "express_status")
 private  String expressStatus;

@Column(name = "pay_status")
 private  String payStatus;

@Column(name = "block_flag")
 private  boolean blockFlag;

@Column(name = "block_reason")
 private  String blockReason;

@Column(name = "refund_apply_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date refundApplyTime;

@Column(name = "refund_finish_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date refundFinishTime;

@Column(name = "pay_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date payTime;

@Column(name = "arrange_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date arrangeTime;

@Column(name = "express_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date expressTime;

@Column(name = "saler_remark")
 private  String salerRemark;

@Column(name = "buyer_message")
 private  String buyerMessage;

@Column(name = "create_time", updatable = false, length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time", length = 32)
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Version
@Column(name = "version")
 private  Integer version;

@OneToMany(fetch = FetchType.EAGER)
@JoinColumn(name = "order_id")
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


public void setRefundApplyTime(Date refundApplyTime){
    this.refundApplyTime = refundApplyTime;
}


public boolean getBlockFlag(){
    return blockFlag;
}


public void setBlockReason(String blockReason){
    this.blockReason = blockReason;
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


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public String getExpressStatus(){
    return expressStatus;
}


public void setOrderGroupNumber(String orderGroupNumber){
    this.orderGroupNumber = orderGroupNumber;
}


public void setId(Integer id){
    this.id = id;
}


public String getOrderGroupNumber(){
    return this.orderGroupNumber;
}


public void setPayTime(Date payTime){
    this.payTime = payTime;
}


public void setArrangeTime(Date arrangeTime){
    this.arrangeTime = arrangeTime;
}


public String getReceiverAddr(){
    return this.receiverAddr;
}


public void setSalerRemark(String salerRemark){
    this.salerRemark = salerRemark;
}


public void setReceiverPhone(String receiverPhone){
    this.receiverPhone = receiverPhone;
}


public void setRefundFinishTime(Date refundFinishTime){
    this.refundFinishTime = refundFinishTime;
}


public boolean isWaitForArrange(String value){
    return WAIT_ARRANGE.getValue().equals(value) || WAIT_ARRANGE_BY_LACKNESS.getValue().equals(value) || WAIT_ARRANGE_BY_MANUAL.getValue().equals(value);
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getRefundApplyTime(){
    return refundApplyTime;
}


public void setExpressCompany(String expressCompany){
    this.expressCompany = expressCompany;
}


public String getpName(){
    return pName;
}


public void setReceiverName(String receiverName){
    this.receiverName = receiverName;
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


public void setReceiverAddr(String receiverAddr){
    this.receiverAddr = receiverAddr;
}


public void setExpressTime(Date expressTime){
    this.expressTime = expressTime;
}


public Date getRefundFinishTime(){
    return refundFinishTime;
}


public void setBuyerMessage(String buyerMessage){
    this.buyerMessage = buyerMessage;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public void setOrderDetails(List<OrderDetail> orderDetails){
    this.orderDetails = orderDetails;
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


public Integer getRepoId(){
    return this.repoId;
}


public void setShopId(Integer shopId){
    this.shopId = shopId;
}


public void setpName(String pName){
    this.pName = pName;
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


public void setExpressNumber(String expressNumber){
    this.expressNumber = expressNumber;
}


public void setShopName(String shopName){
    this.shopName = shopName;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setBlockFlag(boolean blockFlag){
    this.blockFlag = blockFlag;
}


public void setOrderMoney(Double orderMoney){
    this.orderMoney = orderMoney;
}


public void setPayStatus(String payStatus){
    this.payStatus = payStatus;
}


public String getValue(){
    return value;
}


public Integer getCompanyId(){
    return this.companyId;
}


public void setStorageStatus(String storageStatus){
    this.storageStatus = storageStatus;
}


public void setExpressStatus(String expressStatus){
    this.expressStatus = expressStatus;
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