package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Checkin;
public class MaintainRecord {

 private  String recordId;

 private  String checkinId;

 private  Date startTime;

 private  Date endTime;

 private  Date actualEndTime;

 private  Date recordCreatedTime;

 private  Date pickupTime;

 private  String recordDes;

 private  String recordStatus;

 private  String currentStatus;

 private  String ifConfirm;

 private  Checkin checkin;

 private  int count;

 private  Double total;

 private  Date todayTime;

 private  Double discountMoney;


public void setTotal(Double total){
    this.total = total;
}


public void setTodayTime(Date todayTime){
    this.todayTime = todayTime;
}


public void setActualEndTime(Date actualEndTime){
    this.actualEndTime = actualEndTime;
}


public void setRecordDes(String recordDes){
    this.recordDes = recordDes;
}


public String getCheckinId(){
    return checkinId;
}


public void setPickupTime(Date pickupTime){
    this.pickupTime = pickupTime;
}


public String getIfConfirm(){
    return ifConfirm;
}


public Date getRecordCreatedTime(){
    return recordCreatedTime;
}


public Date getEndTime(){
    return endTime;
}


public void setIfConfirm(String ifConfirm){
    this.ifConfirm = ifConfirm;
}


public void setCheckin(Checkin checkin){
    this.checkin = checkin;
}


public String getRecordStatus(){
    return recordStatus;
}


public String getCurrentStatus(){
    return currentStatus;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


public Date getTodayTime(){
    return todayTime;
}


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public Date getPickupTime(){
    return pickupTime;
}


public Date getActualEndTime(){
    return actualEndTime;
}


public void setRecordStatus(String recordStatus){
    this.recordStatus = recordStatus;
}


public void setCheckinId(String checkinId){
    this.checkinId = checkinId;
}


public void setCurrentStatus(String currentStatus){
    this.currentStatus = currentStatus;
}


public String getRecordDes(){
    return recordDes;
}


public String getRecordId(){
    return recordId;
}


public void setRecordCreatedTime(Date recordCreatedTime){
    this.recordCreatedTime = recordCreatedTime;
}


public void setDiscountMoney(Double discountMoney){
    this.discountMoney = discountMoney;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public Double getTotal(){
    return total;
}


@Override
public String toString(){
    return "MaintainRecord{" + "recordId='" + recordId + '\'' + ", checkinId='" + checkinId + '\'' + ", startTime=" + startTime + ", endTime=" + endTime + ", actualEndTime=" + actualEndTime + ", recordCreatedTime=" + recordCreatedTime + ", pickupTime=" + pickupTime + ", recordDes='" + recordDes + '\'' + ", recordStatus='" + recordStatus + '\'' + ", checkin=" + checkin + '}';
}


public Checkin getCheckin(){
    return checkin;
}


public Double getDiscountMoney(){
    return discountMoney;
}


}