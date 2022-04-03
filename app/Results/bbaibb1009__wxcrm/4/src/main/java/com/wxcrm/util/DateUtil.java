package com.wxcrm.util;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {


public Date getMonthLastDate(int year,int month){
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
    return calendar.getTime();
}


public Date getMonday(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(getDateCompute(date, -1));
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return calendar.getTime();
}


public Date parseDate(String date,String pattern){
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    return dateFormat.parse(date);
}


public Date getTimeCompute(Date date,int len){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // System.out.println("����ǰ��"+dateFormat.format(date));
    long time = date.getTime() + (len * 60 * 60 * 1000);
    date.setTime(time);
    // System.out.println("�����"+dateFormat.format(date));
    // System.out.println("----------------------------------------------");
    return date;
}


public String parseString(Date date,String pattern){
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    return dateFormat.format(date);
}


public String getMonthCompute(String curMonth,int months){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(parseDate(curMonth, "yyyy-MM"));
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
    return parseString(calendar.getTime(), "yyyy-MM");
}


public Date getDateCompute(Date date,int days){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
    return calendar.getTime();
}


public Date getSunday(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(getDateCompute(date, 6));
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    return calendar.getTime();
}


public long getDaysBetweenDate(Date dateFrom,Date dateTo){
    int per = 24 * 60 * 60 * 1000;
    return (dateTo.getTime() - dateFrom.getTime()) / per;
}


}