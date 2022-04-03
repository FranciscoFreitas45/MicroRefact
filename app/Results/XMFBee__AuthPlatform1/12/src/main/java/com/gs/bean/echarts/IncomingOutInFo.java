package com.gs.bean.echarts;
 import java.util.Date;
public class IncomingOutInFo {

 private  Double inMoney;

 private  Double outMoney;

 private  String date;

 private  Date dateTime;


public void setDateTime(Date dateTime){
    this.dateTime = dateTime;
}


public Double getOutMoney(){
    return outMoney;
}


public Date getDateTime(){
    return dateTime;
}


public Double getInMoney(){
    return inMoney;
}


public void setOutMoney(Double outMoney){
    this.outMoney = outMoney;
}


public void setDate(String date){
    this.date = date;
}


public void setInMoney(Double inMoney){
    this.inMoney = inMoney;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "IncomingOutInFo{" + "inMoney=" + inMoney + ", outMoney=" + outMoney + ", date='" + date + '\'' + '}';
}


}