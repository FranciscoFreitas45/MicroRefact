package org.jeecgframework.core.util;
 import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.util.StringUtils;
public class DateUtils extends PropertyEditorSupport{

 public  SimpleDateFormat date_sdf;

 public  SimpleDateFormat yyyyMMdd;

 public  SimpleDateFormat date_sdf_wz;

 public  SimpleDateFormat time_sdf;

 public  SimpleDateFormat yyyymmddhhmmss;

 public  SimpleDateFormat short_time_sdf;

 public  SimpleDateFormat datetimeFormat;

 private  long DAY_IN_MILLIS;

 private  long HOUR_IN_MILLIS;

 private  long MINUTE_IN_MILLIS;

 private  long SECOND_IN_MILLIS;


public String formatDate(Date date,String pattern){
    return getSDFormat(pattern).format(date);
}


public String formatShortTime(Date date){
    return short_time_sdf.format(date);
}


public Calendar parseCalendar(String src,String pattern){
    Date date = parseDate(src, pattern);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
}


public int dateDiff(char flag,Calendar calSrc,Calendar calDes){
    long millisDiff = getMillis(calSrc) - getMillis(calDes);
    if (flag == 'y') {
        return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
    }
    if (flag == 'd') {
        return (int) (millisDiff / DAY_IN_MILLIS);
    }
    if (flag == 'h') {
        return (int) (millisDiff / HOUR_IN_MILLIS);
    }
    if (flag == 'm') {
        return (int) (millisDiff / MINUTE_IN_MILLIS);
    }
    if (flag == 's') {
        return (int) (millisDiff / SECOND_IN_MILLIS);
    }
    return 0;
}


public Timestamp gettimestamp(){
    Date dt = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowTime = df.format(dt);
    java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
    return buydate;
}


public String date2Str(Date date,SimpleDateFormat date_sdf){
    if (null == date) {
        return null;
    }
    return date_sdf.format(date);
}


public Timestamp getCalendarTimestamp(Calendar cal){
    // ---------------------return new Timestamp(cal.getTimeInMillis());
    return new Timestamp(cal.getTime().getTime());
}


public String formatDateTime(){
    return datetimeFormat.format(getCalendar().getTime());
}


public String formatTime(Date date){
    return time_sdf.format(date);
}


public String timestamptoStr(Timestamp time){
    Date date = null;
    if (null != time) {
        date = new Date(time.getTime());
    }
    return date2Str(date_sdf);
}


public Date str2Date(String str,SimpleDateFormat sdf){
    if (null == str || "".equals(str)) {
        return null;
    }
    Date date = null;
    try {
        date = sdf.parse(str);
        return date;
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return null;
}


public Calendar getCalendar(long millis){
    Calendar cal = Calendar.getInstance();
    // --------------------cal.setTimeInMillis(millis);
    cal.setTime(new Date(millis));
    return cal;
}


public long getMillis(Timestamp ts){
    return ts.getTime();
}


public String getDataString(SimpleDateFormat formatstr){
    return formatstr.format(getCalendar().getTime());
}


public void setAsText(String text){
    if (StringUtils.hasText(text)) {
        try {
            if (text.indexOf(":") == -1 && text.length() == 10) {
                setValue(this.date_sdf.parse(text));
            } else if (text.indexOf(":") > 0 && text.length() == 19) {
                setValue(this.datetimeFormat.parse(text));
            } else {
                throw new IllegalArgumentException("Could not parse date, date format is error ");
            }
        } catch (ParseException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
            iae.initCause(ex);
            throw iae;
        }
    } else {
        setValue(null);
    }
}


public SimpleDateFormat getSDFormat(String pattern){
    return new SimpleDateFormat(pattern);
}


public Timestamp str2Timestamp(String str){
    Date date = str2Date(str, date_sdf);
    return new Timestamp(date.getTime());
}


public String formatAddDate(String src,String pattern,int amount){
    Calendar cal;
    cal = parseCalendar(src, pattern);
    cal.add(Calendar.DATE, amount);
    return formatDate(cal);
}


public Date parseDate(String src,String pattern){
    return getSDFormat(pattern).parse(src);
}


public int getYear(){
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(getDate());
    return calendar.get(Calendar.YEAR);
}


public Timestamp getTimestamp(Date date){
    return new Timestamp(date.getTime());
}


public Timestamp parseTimestamp(String src,String pattern){
    Date date = parseDate(src, pattern);
    return new Timestamp(date.getTime());
}


public String getDate(String format){
    Date date = new Date();
    if (null == date) {
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
}


public String dateformat(String date,String format){
    SimpleDateFormat sformat = new SimpleDateFormat(format);
    Date _date = null;
    try {
        _date = sformat.parse(date);
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return sformat.format(_date);
}


}