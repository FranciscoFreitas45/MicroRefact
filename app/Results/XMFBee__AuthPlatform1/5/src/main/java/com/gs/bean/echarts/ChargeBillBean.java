package com.gs.bean.echarts;
 public class ChargeBillBean {

 private  String date;

 private  Double maintainMoney;

 private  Double preserveMoney;


public Double getMaintainMoney(){
    return maintainMoney;
}


public void setMaintainMoney(Double maintainMoney){
    this.maintainMoney = maintainMoney;
}


public Double getPreserveMoney(){
    return preserveMoney;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public void setPreserveMoney(Double preserveMoney){
    this.preserveMoney = preserveMoney;
}


}