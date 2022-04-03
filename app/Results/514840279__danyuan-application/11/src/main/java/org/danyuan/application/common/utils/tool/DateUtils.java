package org.danyuan.application.common.utils.tool;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.danyuan.application.common.utils.string.StringUtils;
public class DateUtils {

 public  String YYYYMMDD;

 public  String YYYYMMDDHHmm;

 public  String YYYYMMDDHHmmSS;

/**
 * 构造函数。
 */
private DateUtils() {
}
public int dateDiff(Date stateDate,Date endDate){
    return (int) ((endDate.getTime() - stateDate.getTime()) / (1000 * 60 * 60 * 24));
}


public Date addSecond(Date date,int second){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.SECOND, second);
    return calendar.getTime();
}


public Date addMonth(Date date,int month){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, month);
    return calendar.getTime();
}


public boolean isValidDate(String date,String pattern){
    if (date == null) {
        return false;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
        sdf.setLenient(false);
        sdf.parse(String.valueOf(date));
    } catch (ParseException ex) {
        return false;
    }
    return true;
}


public Date addDay(Date date,int day){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, day);
    return calendar.getTime();
}


public Date getLastDay(Date date){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DATE, 1);
    calendar.roll(Calendar.DATE, -1);
    return calendar.getTime();
}


public Date getFirstDay(Date date){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
}


public Date convertString2Date(String str,String pattern){
    if (str == null || "".equals(str)) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Calendar calendar = Calendar.getInstance();
    try {
        calendar.setTime(sdf.parse(str.trim()));
    } catch (ParseException ex) {
        throw new RuntimeException(ex);
    }
    return calendar.getTime();
}


public int compareDate(Date d1,Date d2){
    if (d1.getTime() > d2.getTime()) {
        return 1;
    } else if (d1.getTime() < d2.getTime()) {
        return -1;
    } else {
        // 相等
        return 0;
    }
}


public int getMinute(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MINUTE);
}


public Date getDate(){
    Calendar calendar = getSystemCalendar();
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
    return DateUtils.convertString2Date(DateUtils.convertDate2String(calendar.getTime(), "yyyyMMdd"), "yyyyMMdd");
}


public String convertDate2String(Date date,String pattern){
    if (date == null) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
}


public int getHour(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.HOUR_OF_DAY);
}


public int getMonth(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) + 1;
}


public Date addHour(Date date,int hour){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hour);
    return calendar.getTime();
}


public String getStringDate(){
    Calendar calendar = getSystemCalendar();
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
    return DateUtils.convertDate2String(calendar.getTime(), "yyyyMMdd");
}


public Date getDateTime(){
    return getSystemDate();
}


public Date addMinute(Date date,int min){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, min);
    return calendar.getTime();
}


public Calendar getSystemCalendar(){
    return Calendar.getInstance();
}


public boolean isLeapYear(int year){
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        return true;
    } else {
        return false;
    }
}


public int getDaysIn2Day(String dateStrFrom,String dateStrTo){
    if (StringUtils.isEmpty(dateStrFrom) || StringUtils.isEmpty(dateStrTo)) {
        return 0;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar fromCalendar = Calendar.getInstance();
    Calendar toCalendar = Calendar.getInstance();
    try {
        fromCalendar.setTime(sdf.parse(dateStrFrom.trim()));
        toCalendar.setTime(sdf.parse(dateStrTo.trim()));
    } catch (ParseException ex) {
        throw new RuntimeException(ex);
    }
    long day = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (24 * 60 * 60 * 1000);
    return ConvUtils.convToInt(day);
}


public int getDay(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DATE);
}


public Date addYear(Date date,int year){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, year);
    return calendar.getTime();
}


public String getTimeStamp(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    return sdf.format(getSystemDate());
}


public int getYear(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
}


public int getSecond(Date date){
    if (date == null) {
        return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.SECOND);
}


public Date getSystemDate(){
    return new Date();
}


public Date formatStr2Date(String dateStr,String pattern){
    if (StringUtils.isEmpty(dateStr)) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmSS);
    if (!StringUtils.isEmpty(pattern)) {
        sdf = new SimpleDateFormat(pattern);
    }
    Date retDate;
    try {
        retDate = sdf.parse(dateStr);
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
    return retDate;
}


}