package com.uec.imonitor.common.base;
 import java.util.Date;
import com.uec.imonitor.common.util.CommonUtil;
public class CommonDate {

 private  Date date;

 private  String year;

 private  String month;

 private  String day;

public CommonDate() {
}public CommonDate(Date date) {
    this.date = date;
    this.year = CommonUtil.getYear(date);
    this.month = CommonUtil.getMonth(date);
    this.day = CommonUtil.getDay(date);
}
public void setMonth(String month){
    this.month = month;
}


public String getYear(){
    return year;
}


public void setDay(String day){
    this.day = day;
}


public void setDate(Date date){
    this.date = date;
}


public Date getDate(){
    return date;
}


public String getMonth(){
    return month;
}


public void setYear(String year){
    this.year = year;
}


public String getDay(){
    return day;
}


}