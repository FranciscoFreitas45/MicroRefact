package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.MaintainRecord;
public class ChargeBill {

 private  String chargeBillId;

 private  String maintainRecordId;

 private  Double chargeBillMoney;

 private  String paymentMethod;

 private  Double actualPayment;

 private  Date chargeTime;

 private  Date chargeCreatedTime;

 private  String chargeBillDes;

 private  String chargeBillStatus;

 private  String cdStatus;

 private  MaintainRecord maintainRecord;


public void setChargeBillDes(String chargeBillDes){
    this.chargeBillDes = chargeBillDes;
}


public void setMaintainRecordId(String maintainRecordId){
    this.maintainRecordId = maintainRecordId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public void setChargeBillStatus(String chargeBillStatus){
    this.chargeBillStatus = chargeBillStatus;
}


public String getChargeBillId(){
    return chargeBillId;
}


public void setChargeTime(Date chargeTime){
    this.chargeTime = chargeTime;
}


public Double getActualPayment(){
    return actualPayment;
}


public void setChargeBillMoney(Double chargeBillMoney){
    this.chargeBillMoney = chargeBillMoney;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public String getChargeBillStatus(){
    return chargeBillStatus;
}


public String getPaymentMethod(){
    return paymentMethod;
}


public Date getChargeTime(){
    return chargeTime;
}


public void setPaymentMethod(String paymentMethod){
    this.paymentMethod = paymentMethod;
}


public void setMaintainRecord(MaintainRecord maintainRecord){
    this.maintainRecord = maintainRecord;
}


public void setActualPayment(Double actualPayment){
    this.actualPayment = actualPayment;
}


public void setChargeBillId(String chargeBillId){
    this.chargeBillId = chargeBillId;
}


public void setChargeCreatedTime(Date chargeCreatedTime){
    this.chargeCreatedTime = chargeCreatedTime;
}


public String getCdStatus(){
    return cdStatus;
}


public Date getChargeCreatedTime(){
    return chargeCreatedTime;
}


public void setCdStatus(String cdStatus){
    this.cdStatus = cdStatus;
}


public Double getChargeBillMoney(){
    return chargeBillMoney;
}


public String getChargeBillDes(){
    return chargeBillDes;
}


}