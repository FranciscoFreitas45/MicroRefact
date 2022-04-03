package org.gliderwiki.framework.util;
 import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
public class DateUtil {

 public  Map monthName;


public String getTodayTime(){
    return getDate(new Date(), "yyyy.MM.dd HH:mm:ss");
}


public int getDefaultOffsetDays(String strDate){
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date param_date = fmt.parse(strDate);
    return getOffsetDays(param_date, new Date());
}


public String getTodayTimeForDB(){
    return getDate(new Date(), "yyyy/MM/dd HH:mm:ss");
}


public boolean getCompareDate(Date pDate1,Date pDate2){
    Calendar date1 = Calendar.getInstance();
    Calendar date2 = Calendar.getInstance();
    boolean ret = false;
    if (pDate1 == null)
        pDate1 = new Date();
    if (pDate2 == null)
        pDate2 = new Date();
    try {
        date1.setTime(pDate1);
        date2.setTime(pDate2);
        ret = date1.before(date2);
    } catch (Exception e) {
    }
    return ret;
}


public String getTodayTimeStamp(){
    return getDate(new Date(), "yyyy-MM-dd HH:mm:ss");
}


public String getOffsetDate(Date pDate,int offset,String pFormat){
    SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
    Calendar c = Calendar.getInstance();
    String ret = "";
    try {
        c.setTime(pDate);
        c.add(Calendar.DAY_OF_MONTH, offset);
        ret = fmt.format(c.getTime());
    } catch (Exception e) {
    }
    return ret;
}


public String getDateOfNextWeek(int dayOfWeek,String format){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK) + 7;
    return getOffsetDate(diff, format);
}


public Date addDate(Date pDate,int pField,int pAmount){
    if (pDate == null)
        pDate = new Date();
    Calendar ret = Calendar.getInstance();
    ret.setTime(pDate);
    ret.add(pField, pAmount);
    return ret.getTime();
}


public String getChangedDateFormat(String strDate,String pBeforeFormat,String pAfterFormat){
    if (strDate == null || "".equals(strDate))
        return "";
    Date date = null;
    try {
        SimpleDateFormat fmt = new SimpleDateFormat(pBeforeFormat);
        date = fmt.parse(strDate);
    } catch (Exception e) {
        return "<font color=red>날짜 형식 에러 : " + e.toString() + "</font>";
    }
    return getDate(date, pAfterFormat);
}


public String getOffsetMonth(int offset,String pFormat){
    Date date = new Date();
    return getOffsetMonth(date, offset, pFormat);
}


public String getDateOfThisWeek(int dayOfWeek,String format){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK);
    return getOffsetDate(diff, format);
}


public String getDayOfWeek(Calendar cal){
    return getDayOfWeek(cal, Locale.KOREA);
}


public Date getOffsetDateToDate(String strDate,int offset){
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
    Date date = fmt.parse(strDate);
    return getOffsetDateToDate(date, offset);
}


public Date getDate(String strDate,String pFormat){
    if (strDate == null)
        return null;
    return new SimpleDateFormat(pFormat).parse(strDate, new ParsePosition(0));
}


public String getCurrentTime(){
    Calendar dateTime = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String str = formatter.format(dateTime.getTime());
    return str;
}


public String getDateOfWeekByDay(int dayOfWeek,String format){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK);
    if (diff > 0) {
        diff = diff - 7;
    }
    return getOffsetDate(diff, format);
}


public int getOffsetDays(int strDate,int strDate2){
    return getOffsetDays(Integer.toString(strDate), Integer.toString(strDate2), "yyyyMMdd");
}


public String getDateOfPreWeek(int dayOfWeek,String format){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK) - 7;
    return getOffsetDate(diff, format);
}


public String getToday(String pFormat){
    return getDate(new Date(), pFormat);
}


}