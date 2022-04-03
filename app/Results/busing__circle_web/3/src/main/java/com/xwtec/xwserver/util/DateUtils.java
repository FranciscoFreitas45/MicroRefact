package com.xwtec.xwserver.util;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
public class DateUtils {

 private  Logger log;

 private  long DAY_MILLIS;

 private  SimpleDateFormat DATE_FORMAT;

 private  SimpleDateFormat monthFmt;


public String getEndDayByWeek(String year,String month,String week){
    String firstMondayDate = "";
    String date = "";
    try {
        firstMondayDate = getFirstMondyOfMonth(year, month);
        date = getAfterDate(firstMondayDate, (Integer.parseInt(week) - 1) * 7 + 6);
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return date;
}


public int getWeeksOfMonth(String year,String month){
    // 默认一个月有4周
    int count = 4;
    try {
        String mondayDate = getAfterDate(getFirstMondyOfMonth(year, month), 28);
        if (mondayDate.substring(4, 6).equals(month)) {
            count = 5;
        }
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return count;
}


public String format(Date date,String formatPattern){
    DateFormat dateFormat = new SimpleDateFormat(formatPattern);
    return dateFormat.format(date);
}


public String getEndDateByMonth(String year,String month){
    SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
    Calendar calendar = Calendar.getInstance();
    try {
        Date date = format.parse(year + month);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    } catch (ParseException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return format2.format(calendar.getTime());
}


public Date parse(String date,String formatPattern){
    DateFormat dateFormat = new SimpleDateFormat(formatPattern);
    Date _date = null;
    try {
        _date = dateFormat.parse(date);
    } catch (ParseException pe) {
        throw pe;
    }
    return _date;
}


public String formatChar8(Date date){
    return format(date, "yyyyMMdd");
}


public String getFirstMondyOfMonth(String year,String month){
    String date = getStartDateByMonth(year, month);
    return getAfterDate(date, nextMondayDays(getWeekByDate(date)));
}


public boolean isSameMonth(String beginDate,String endDate){
    try {
        Date begin = DATE_FORMAT.parse(beginDate);
        Date end = DATE_FORMAT.parse(endDate);
        Calendar cBegin = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cBegin.setTime(begin);
        cEnd.setTime(end);
        if (cBegin.get(Calendar.YEAR) == cEnd.get(Calendar.YEAR) && cBegin.get(Calendar.MONTH) == cEnd.get(Calendar.MONTH)) {
            // 同一年同一个月
            return true;
        }
    } catch (ParseException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return false;
}


public String getStartDateByMonth(String year,String month){
    return year + month + "01";
}


public int getWeekByDate(String dateValue){
    // 再转换为时间
    Date date = strToDate(dateValue);
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.DAY_OF_WEEK);
}


public String getStartDayByWeek(String year,String month,String week){
    String firstMondayDate = "";
    String date = "";
    try {
        firstMondayDate = getFirstMondyOfMonth(year, month);
        date = getAfterDate(firstMondayDate, (Integer.parseInt(week) - 1) * 7);
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return date;
}


public String getAfterDate(String date,int several){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Calendar cal = Calendar.getInstance();
    cal.setTime(sdf.parse(date));
    long days = several;
    long timeone = cal.getTimeInMillis();
    long result = timeone + days * 24 * 60 * 60 * 1000;
    Date getDate = new Date(result);
    return sdf.format(getDate).toString();
}


public String getCurrent8(){
    return formatChar8(getCurrent().getTime());
}


public boolean isSameWeek(String beginDate,String endDate){
    try {
        Date begin = DATE_FORMAT.parse(beginDate);
        Date end = DATE_FORMAT.parse(endDate);
        Calendar cBegin = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cBegin.setTime(begin);
        cEnd.setTime(end);
        // 计算两个日期之间相差多少天
        int distance = getDayDistance(cBegin.getTimeInMillis(), cEnd.getTimeInMillis());
        if (distance < 7 && cBegin.get(Calendar.WEEK_OF_YEAR) == cEnd.get(Calendar.WEEK_OF_YEAR)) {
            return true;
        }
    } catch (ParseException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return false;
}


public String getWeekOfMonth(String date){
    int y = Integer.parseInt(date.substring(0, 4));
    int m = Integer.parseInt(date.substring(4, 6));
    int d = Integer.parseInt(date.substring(6));
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, y);
    cal.set(Calendar.MONTH, m - 1);
    cal.set(Calendar.DAY_OF_MONTH, d);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    // 取本月第一天是星期几
    Calendar cal2 = Calendar.getInstance();
    cal2.set(Calendar.YEAR, y);
    cal2.set(Calendar.MONTH, m - 1);
    cal2.set(Calendar.DAY_OF_MONTH, 1);
    cal2.set(Calendar.HOUR, 0);
    cal2.set(Calendar.MINUTE, 0);
    cal2.set(Calendar.SECOND, 0);
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    int week = cal2.get(Calendar.DAY_OF_WEEK);
    // 判断本月第一天是否是周一，如果是则周数不需要-1，否则周数需要-1
    if (week == Calendar.MONDAY) {
        return monthFmt.format(cal.getTime()) + cal.get(Calendar.WEEK_OF_MONTH);
    } else {
        return monthFmt.format(cal.getTime()) + (cal.get(Calendar.WEEK_OF_MONTH) - 1);
    }
}


public boolean isSameYear(String beginDate,String endDate){
    try {
        Date begin = DATE_FORMAT.parse(beginDate);
        Date end = DATE_FORMAT.parse(endDate);
        Calendar cBegin = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cBegin.setTime(begin);
        cEnd.setTime(end);
        if (cBegin.get(Calendar.YEAR) == cEnd.get(Calendar.YEAR)) {
            // 同一年
            return true;
        }
    } catch (ParseException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return false;
}


public String formatChar14(Date date){
    return format(date, "yyyyMMddHHmmss");
}


public String getCurrent14(){
    return formatChar14(getCurrent().getTime());
}


public Date strToDate(String dateValue){
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    return format.parse(dateValue);
}


public String getLastMonth(){
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, -1);
    return monthFmt.format(c.getTime());
}


public Calendar getCurrent(){
    return getCurrent(Locale.getDefault());
}


public boolean isAfter(Date afterDate,Date beforeDate,boolean allowEquals){
    return allowEquals ? afterDate.after(beforeDate) || afterDate.compareTo(beforeDate) == 0 : afterDate.after(beforeDate);
}


public int nextMondayDays(int day){
    int days = 0;
    switch(day) {
        case 1:
            days = 1;
            break;
        case 2:
            days = 0;
            break;
        case 3:
            days = 6;
            break;
        case 4:
            days = 5;
            break;
        case 5:
            days = 4;
            break;
        case 6:
            days = 3;
            break;
        case 7:
            days = 2;
            break;
        default:
            break;
    }
    return days;
}


public int getDayDistance(long start,long end){
    long temp = end - start;
    int distance = (int) (temp / DAY_MILLIS);
    return distance;
}


}