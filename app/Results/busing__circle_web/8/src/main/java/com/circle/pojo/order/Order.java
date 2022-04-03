package com.circle.pojo.order;
 import java.util.List;
import java.util.Map;
public class Order implements Cloneable{

 private  String orderId;

 private  int userId;

 private  String userName;

 private  String orderNo;

 private  int status;

 private  int payStatus;

 private  int shipStatus;

 private  int circleId;

 private  String circleName;

 private  int organizerId;

 private  String organizerName;

 private  String orderTime;

 private  String issueAddress;

 private  String issueTime;

 private  int batchId;

 private  String receiveName;

 private  String receivePhone;

 private  int shipType;

 private  int payType;

 private  String endTime;

 private  int paymentId;

 private  String paymentName;

 private  String payNo;

 private  int goodsAmount;

 private  double orderAmount;

 private  String completeTime;

 private  String signName;

 private  String signTime;

 private  String shipTime;

 private  String remark;

 private  String adminRemark;

 public  List<Map<String,Object>> orderDetailList;


public int getShipType(){
    return shipType;
}


public void setPaymentName(String paymentName){
    this.paymentName = paymentName;
}


public String getOrderId(){
    return orderId;
}


public int getCircleId(){
    return circleId;
}


public void setOrderId(String orderId){
    this.orderId = orderId;
}


public int getStatus(){
    return status;
}


public void setSignTime(String signTime){
    this.signTime = signTime;
}


public void setPayType(int payType){
    this.payType = payType;
}


public String getEndTime(){
    return endTime;
}


public int getPayType(){
    return payType;
}


public String getOrderTime(){
    return orderTime;
}


public void setOrderTime(String orderTime){
    this.orderTime = orderTime;
}


public void setReceiveName(String receiveName){
    this.receiveName = receiveName;
}


public void setRemark(String remark){
    this.remark = remark;
}


public int getShipStatus(){
    return shipStatus;
}


public String getShipTime(){
    return shipTime;
}


public String getRemark(){
    return remark;
}


public String getUserName(){
    return userName;
}


public void setOrganizerId(int organizerId){
    this.organizerId = organizerId;
}


public void setIssueAddress(String issueAddress){
    this.issueAddress = issueAddress;
}


public String getCompleteTime(){
    return completeTime;
}


public void setOrganizerName(String organizerName){
    this.organizerName = organizerName;
}


public String getOrderNo(){
    return orderNo;
}


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


public String getIssueAddress(){
    return issueAddress;
}


public String getPayNo(){
    return payNo;
}


public void setShipStatus(int shipStatus){
    this.shipStatus = shipStatus;
}


public void setReceivePhone(String receivePhone){
    this.receivePhone = receivePhone;
}


public List<Map<String,Object>> getOrderDetailList(){
    return orderDetailList;
}


public String getReceiveName(){
    return receiveName;
}


public int getPayStatus(){
    return payStatus;
}


public String getAdminRemark(){
    return adminRemark;
}


public int getBatchId(){
    return batchId;
}


public String getSignName(){
    return signName;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setIssueTime(String issueTime){
    this.issueTime = issueTime;
}


public void setAdminRemark(String adminRemark){
    this.adminRemark = adminRemark;
}


public void setPayNo(String payNo){
    this.payNo = payNo;
}


public String getOrganizerName(){
    return organizerName;
}


public int getGoodsAmount(){
    return goodsAmount;
}


public String getSignTime(){
    return signTime;
}


public void setGoodsAmount(int goodsAmount){
    this.goodsAmount = goodsAmount;
}


public void setBatchId(int batchId){
    this.batchId = batchId;
}


public void setCircleId(int circleId){
    this.circleId = circleId;
}


public int getPaymentId(){
    return paymentId;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setOrderAmount(double orderAmount){
    this.orderAmount = orderAmount;
}


public void setCircleName(String circleName){
    this.circleName = circleName;
}


public void setShipType(int shipType){
    this.shipType = shipType;
}


public String getReceivePhone(){
    return receivePhone;
}


public String getIssueTime(){
    return issueTime;
}


public void setCompleteTime(String completeTime){
    this.completeTime = completeTime;
}


public void setPaymentId(int paymentId){
    this.paymentId = paymentId;
}


public void setShipTime(String shipTime){
    this.shipTime = shipTime;
}


public void setStatus(int status){
    this.status = status;
}


public void setPayStatus(int payStatus){
    this.payStatus = payStatus;
}


public void setSignName(String signName){
    this.signName = signName;
}


public String getCircleName(){
    return circleName;
}


public void setEndTime(String endTime){
    this.endTime = endTime;
}


@Override
public Object clone(){
    return super.clone();
}


public String getPaymentName(){
    return paymentName;
}


public int getOrganizerId(){
    return organizerId;
}


public double getOrderAmount(){
    return orderAmount;
}


public int getUserId(){
    return userId;
}


public void setOrderDetailList(List<Map<String,Object>> orderDetailList){
    this.orderDetailList = orderDetailList;
}


}