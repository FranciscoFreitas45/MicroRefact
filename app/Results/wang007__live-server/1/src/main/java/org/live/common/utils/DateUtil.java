package org.live.common.utils;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {

 private  int x;

 private  int y;

 private  Calendar localTime;


public Date afterAFewMinute(Date date,int minute){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minute);
    return calendar.getTime();
}


public String nowDate(String pattern){
    return nowDateAddDays4Format(0, pattern);
}


public Double differenceDay(String s1,String s2,String dateFormat) throws ParseException{
    Double dm = differenceMilliseconds(s1, s2, dateFormat).doubleValue();
    dm = dm / (1000 * 60 * 60 * 24);
    return dm;
}


public String thisYear(){
    x = localTime.get(Calendar.YEAR);
    return x + "-01" + "-01";
}


public String getJdbs(Date date){
    String result = DateUtil.date2String(date, "yyyy");
    int yf = Integer.parseInt(DateUtil.date2String(date, "MM"));
    switch(yf) {
        case 1:
        case 2:
        case 3:
            result += "1";
            break;
        case 4:
        case 5:
        case 6:
            result += "2";
            break;
        case 7:
        case 8:
        case 9:
            result += "3";
            break;
        case 10:
        case 11:
        case 12:
            result += "4";
            break;
    }
    return result;
}


public Double differenceHour(String s1,String s2,String dateFormat) throws ParseException{
    Double dm = differenceMilliseconds(s1, s2, dateFormat).doubleValue();
    dm = dm / (1000 * 60 * 60);
    return dm;
}


public String date2String(Date date,String format){
    DateFormat dateFormat = new SimpleDateFormat(format);
    try {
        return dateFormat.format(date);
    } catch (Exception e) {
        return null;
    }
}


public Date afterAFewYear(Date date,int year){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, year);
    return calendar.getTime();
}


public Date afterAFewDays(int days){
    Date date = new Date();
    return afterAFewDays(date, days);
}


public String nowDateAddDays(Integer aDate){
    SimpleDateFormat dateFormatf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, aDate);
    String d = dateFormatf.format(calendar.getTime());
    return d;
}


public String date2DateString(Date date){
    return date2String(date, "yyyy-MM-dd");
}


public Date string2Date(String source){
    return string2Date(source, "yyyy-MM-dd");
}


public Double differenceYears(String s1,String s2,String dateFormat) throws ParseException{
    Double dm = differenceMonth(s1, s2, dateFormat).doubleValue();
    dm = dm / 12;
    return dm;
}


public String nowDateAddDays4Format(Integer aDate,String pattern){
    SimpleDateFormat dateFormatf = new SimpleDateFormat(pattern);
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, aDate);
    String d = dateFormatf.format(calendar.getTime());
    return d;
}


public void main(String[] args){
    // System.out.println(nowDate());
    // System.out.println(afterAFewYear(new Date(), 1));
    System.out.println(nowTime());
    System.out.println(afterAFewHour(new Date(), 0));
    System.out.println(afterAFewMinute(new Date(), 61));
}


public Double differenceMonth(String s1,String s2,String dateFormat) throws ParseException{
    Double dm = differenceDay(s1, s2, dateFormat).doubleValue();
    dm = dm / 30;
    return dm;
}


public String nowTime(){
    return nowDateAddDays(0);
}


public Calendar getLocalTime(){
    return localTime;
}


public Double differenceWeek(String s1,String s2,String dateFormat) throws ParseException{
    Double dm = differenceDay(s1, s2, dateFormat).doubleValue();
    dm = dm / 7;
    return dm;
}


public Long differenceMilliseconds(String s1,String s2,String dateFormat) throws ParseException{
    SimpleDateFormat dateFormatf = new SimpleDateFormat(dateFormat);
    Date time1 = dateFormatf.parse(s1);
    Date time2 = dateFormatf.parse(s2);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(time1);
    Long mm1 = calendar.getTimeInMillis();
    calendar.setTime(time2);
    Long mm2 = calendar.getTimeInMillis();
    Long dm = mm1 - mm2;
    return dm;
}


public String date2TimeString(Date date){
    return date2String(date, "yyyy-MM-dd HH:mm:ss");
}


public String thisMonth(){
    String strY = null;
    x = localTime.get(Calendar.YEAR);
    y = localTime.get(Calendar.MONTH) + 1;
    strY = y >= 10 ? String.valueOf(y) : ("0" + y);
    return x + "-" + strY + "-01";
}


public Date afterAFewHour(Date date,int hour){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR, hour);
    return calendar.getTime();
}


public void setLocalTime(Calendar localTime){
    this.localTime = localTime;
}


}