package com.gs.bean.echarts;
 public class MaintainRecordBean {

 private  String date;

 private  int maintainCount;

 private  int preserveCount;


public int getPreserveCount(){
    return preserveCount;
}


public void setMaintainCount(int maintainCount){
    this.maintainCount = maintainCount;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public void setPreserveCount(int preserveCount){
    this.preserveCount = preserveCount;
}


public int getMaintainCount(){
    return maintainCount;
}


}