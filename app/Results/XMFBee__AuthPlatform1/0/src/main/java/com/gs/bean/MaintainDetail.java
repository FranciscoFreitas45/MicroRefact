package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.MaintainFix;
import com.gs.Interface.MaintainRecord;
public class MaintainDetail {

 private  String maintainDetailId;

 private  String maintainRecordId;

 private  String maintainItemId;

 private  Double maintainDiscount;

 private  Date mdcreatedTime;

 private  String mdStatus;

 private  MaintainFix maintainFix;

 private  MaintainRecord maintainRecord;

 private  int count;

 private  String disCount;


public String getMaintainItemId(){
    return maintainItemId;
}


public String getMdStatus(){
    return mdStatus;
}


public MaintainFix getMaintainFix(){
    return maintainFix;
}


public String getMaintainDetailId(){
    return maintainDetailId;
}


public void setMaintainRecordId(String maintainRecordId){
    this.maintainRecordId = maintainRecordId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public void setMaintainDiscount(Double maintainDiscount){
    this.maintainDiscount = maintainDiscount;
}


public String getDisCount(){
    return disCount;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public void setMdcreatedTime(Date mdcreatedTime){
    this.mdcreatedTime = mdcreatedTime;
}


public Double getMaintainDiscount(){
    return maintainDiscount;
}


public Date getMdcreatedTime(){
    return mdcreatedTime;
}


public void setMaintainRecord(MaintainRecord maintainRecord){
    this.maintainRecord = maintainRecord;
}


@Override
public String toString(){
    return "MaintainDetail{" + "maintainDetailId='" + maintainDetailId + '\'' + ", maintainRecordId='" + maintainRecordId + '\'' + ", maintainItemId='" + maintainItemId + '\'' + ", maintainDiscount=" + maintainDiscount + ", mdcreatedTime=" + mdcreatedTime + '}';
}


public void setDisCount(String disCount){
    this.disCount = disCount;
}


public void setMaintainDetailId(String maintainDetailId){
    this.maintainDetailId = maintainDetailId;
}


public int getCount(){
    return count;
}


public void setMaintainItemId(String maintainItemId){
    this.maintainItemId = maintainItemId;
}


public void setCount(int count){
    this.count = count;
}


public void setMaintainFix(MaintainFix maintainFix){
    this.maintainFix = maintainFix;
}


public void setMdStatus(String mdStatus){
    this.mdStatus = mdStatus;
}


}