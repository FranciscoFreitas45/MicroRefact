package com.gs.DTO;
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


public void setTodayTime(Date todayTime){
    this.todayTime = todayTime;
}


public void setRecordDes(String recordDes){
    this.recordDes = recordDes;
}


public String getCheckinId(){
    return checkinId;
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


public int getCount(){
    return count;
}


public Date getTodayTime(){
    return todayTime;
}


public Date getPickupTime(){
    return pickupTime;
}


public Date getActualEndTime(){
    return actualEndTime;
}


public void setCheckinId(String checkinId){
    this.checkinId = checkinId;
}


public String getRecordDes(){
    return recordDes;
}


public String getRecordId(){
    return recordId;
}


public void setDiscountMoney(Double discountMoney){
    this.discountMoney = discountMoney;
}


public Double getTotal(){
    return total;
}


public Checkin getCheckin(){
    return checkin;
}


public Double getDiscountMoney(){
    return discountMoney;
}


}