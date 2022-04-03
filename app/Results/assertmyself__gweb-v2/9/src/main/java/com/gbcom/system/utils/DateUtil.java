package com.gbcom.system.utils;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.op.util.Assert;
import com.hc.core.utils.XMLGregorianCalendarConversionUtils;
public class DateUtil {

 public  Logger LOG;


public Date add(Date date,int field,int amount){
    Calendar calendar = getCalendar(date);
    calendar.add(field, amount);
    return calendar.getTime();
}


public Date getTodayTime(Date date){
    Calendar cNow = getCalendar(new Date());
    Calendar calendar = getCalendar(date);
    calendar.set(Calendar.YEAR, cNow.get(Calendar.YEAR));
    calendar.set(Calendar.MONTH, cNow.get(Calendar.MONTH));
    calendar.set(Calendar.DAY_OF_YEAR, cNow.get(Calendar.DAY_OF_YEAR));
    return calendar.getTime();
}


public int sub(Date date1,Date date2){
    Assert.notNull(date1);
    Assert.notNull(date2);
    long tem = date1.getTime() - date2.getTime();
    return Integer.parseInt(String.valueOf(tem / (24 * 60 * 60 * 1000)));
}


public String valueOfSecond(long time){
    long h = time / 3600;
    long m = (time % 3600) / 60;
    long s = (time % 3600) % 60;
    String value = h + "Basic_hour" + ":" + m + "Basic_min" + ":" + s + "Basic_sec";
    return value;
}


public String valueOfMinute(long time){
    long d = time / (24 * 60);
    long h = (time % (24 * 60)) / 60;
    long m = (time % (24 * 60)) % 60;
    String value = d + "Basic_day" + ":" + h + "Basic_hour" + ":" + m + "Basic_min";
    return value;
}


public Date getCurMonthStart(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime();
}


public String format(Object date,String pattern){
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    if (date != null) {
        return sdf.format(date);
    } else {
        return null;
    }
}


public void main(String[] args){
    // //
    // System.out.println(format(parse("20131111235959","yyyyMMddHHmmss")));
    // 
    // // LOG.info("DATA="+parse("TTT20131111235959","dsdsdsd"));
    // long var = 10201;
    // System.out.println(valueOfMinute(var));
    // System.out.println(valueOfSecond(var));
    // 
    // 
    // 
    // Timestamp curTime = new Timestamp(System.currentTimeMillis());
    // XMLGregorianCalendar calendar = timeToXmlDate(curTime);
    // 
    // System.out.println("calendar = " + calendar);
    // 
    // Timestamp timestamp = xmlDate2Time(calendar);
    // System.out.println("timestamp = " +
    // DateTimeHelper.formatTimeGBK(timestamp));
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = df.format(getCurWeekStart(new Date()));
    System.out.println(time);
}


public Date parse(Object date,String pattern){
    Date fd = new Date();
    if (date == null) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
        fd = sdf.parse(date.toString());
    } catch (ParseException e) {
        LOG.error("parse date failed!", e);
    }
    return fd;
}


public int getWeek(Date date){
    Calendar calendar = getCalendar(date);
    return calendar.get(Calendar.DAY_OF_WEEK);
}


public Calendar getCalendar(Date date){
    if (date == null) {
        return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
}


public java.sql.Timestamp xmlDate2Time(XMLGregorianCalendar cal){
    if (cal != null) {
        return new java.sql.Timestamp(XMLGregorianCalendarConversionUtils.asDate(cal).getTime());
    }
    return null;
}


public Date getCurWeekStart(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return calendar.getTime();
}


@SuppressWarnings("deprecation")
public Date getFirstDayOfMonth(int num,boolean isPositive){
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    int year = calendar.getTime().getYear();
    int month = calendar.getTime().getMonth();
    if (isPositive) {
        month = month + num;
    } else {
        month = month - num;
    }
    int day = 1;
    if (month < 0) {
        year = year - 1;
        month = 11;
    } else if (month > 12) {
        year = year + 1;
        month = 0;
    }
    date = new Date(year, month, day);
    return date;
}


public Date getCurDayStart(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime();
}


public String[] getWorkDate(String date){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cd = Calendar.getInstance();
    cd.setTime(simpleDateFormat.parse(date));
    cd.add(Calendar.HOUR, 7);
    cd.add(Calendar.MINUTE, 30);
    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String[] workDate = new String[2];
    workDate[0] = simpleDateFormat.format(cd.getTime());
    cd.add(Calendar.HOUR, 10);
    workDate[1] = simpleDateFormat.format(cd.getTime());
    return workDate;
}


public Date parseDate(Object date,String pattern){
    Date d = new Date();
    if (date == null) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    String format = sdf.format(date);
    try {
        d = sdf.parse(format);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return d;
}


@SuppressWarnings("deprecation")
public Date mergeDate(Date date,Date time){
    Calendar calendar = getCalendar(date);
    calendar.set(Calendar.MINUTE, time.getMinutes());
    calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
    return calendar.getTime();
}


public Date xmlDate2Date(XMLGregorianCalendar cal){
    return cal.toGregorianCalendar().getTime();
}


public Date getCurYearStart(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime();
}


public Date getDate(Date beginDate,int data){
    Calendar beginCal = Calendar.getInstance();
    beginCal.setTimeInMillis(beginDate.getTime());
    GregorianCalendar calendar = new GregorianCalendar(beginCal.get(Calendar.YEAR), beginCal.get(Calendar.MONTH), beginCal.get(Calendar.DATE), beginCal.get(Calendar.HOUR_OF_DAY), beginCal.get(Calendar.MINUTE), beginCal.get(Calendar.SECOND));
    calendar.add(GregorianCalendar.DATE, data);
    return new Date(calendar.getTimeInMillis());
}


public XMLGregorianCalendar dateToXmlDate(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    DatatypeFactory dtf = null;
    try {
        dtf = DatatypeFactory.newInstance();
    } catch (DatatypeConfigurationException e) {
    }
    XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
    dateType.setYear(cal.get(Calendar.YEAR));
    // 由于Calendar.MONTH取值范围为0~11,需要加1
    dateType.setMonth(cal.get(Calendar.MONTH) + 1);
    dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
    dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
    dateType.setMinute(cal.get(Calendar.MINUTE));
    dateType.setSecond(cal.get(Calendar.SECOND));
    return dateType;
}


public XMLGregorianCalendar timeToXmlDate(java.sql.Timestamp orgTime){
    if (orgTime != null) {
        return XMLGregorianCalendarConversionUtils.asXMLGregorianCalendar(new Date(orgTime.getTime()));
    }
    return null;
}


}