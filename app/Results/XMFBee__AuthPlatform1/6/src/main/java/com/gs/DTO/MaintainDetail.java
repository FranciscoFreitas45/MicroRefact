package com.gs.DTO;
 import java.util.Date;
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


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public String getDisCount(){
    return disCount;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public Double getMaintainDiscount(){
    return maintainDiscount;
}


public Date getMdcreatedTime(){
    return mdcreatedTime;
}


@Override
public String toString(){
    return "MaintainDetail{" + "maintainDetailId='" + maintainDetailId + '\'' + ", maintainRecordId='" + maintainRecordId + '\'' + ", maintainItemId='" + maintainItemId + '\'' + ", maintainDiscount=" + maintainDiscount + ", mdcreatedTime=" + mdcreatedTime + '}';
}


public void setMaintainDetailId(String maintainDetailId){
    this.maintainDetailId = maintainDetailId;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


public void setMdStatus(String mdStatus){
    this.mdStatus = mdStatus;
}


}