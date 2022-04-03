package com.byr.warehouse.util;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {

 public  SimpleDateFormat format;


public Date stringToDate(String s){
    Date date = null;
    try {
        date = format.parse(s);
    } catch (ParseException e) {
        System.out.println(e.getMessage());
    }
    return date;
}


public Date getDateBefore(Date today,int beforeDays){
    // 获取当前时间毫秒数
    long time = today.getTime();
    // 获取7天的毫秒数
    long sevenTime = beforeDays * 24 * 60 * 60 * 1000;
    // 当前时间毫秒数-7天的毫秒数=7天之间那天的毫秒数
    long times = time - sevenTime;
    // 将毫秒数转日期
    Date dat = new Date(times);
    return dat;
}


public String dateToString(Date date){
    String dateStr = null;
    dateStr = format.format(date);
    return dateStr;
}


}