package org.sdrc.childinfo.model;
 public class PtcMail extends Mail{

 private  String areaCode;

 private  String timePeriod;

 private  String status;

 private  String remark;

 private  String areaName;


public String getAreaCode(){
    return areaCode;
}


public String getTimePeriod(){
    return timePeriod;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
}


public String getRemark(){
    return remark;
}


public String getAreaName(){
    return areaName;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


public void setTimePeriod(String timePeriod){
    this.timePeriod = timePeriod;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


}