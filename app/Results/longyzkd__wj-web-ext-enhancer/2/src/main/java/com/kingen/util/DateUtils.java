package com.kingen.util;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DateUtils {

 protected  Logger logger;

 private  String[] parsePatterns;


public String formatDate(Date date,Object pattern){
    String formatDate = null;
    if (pattern != null && pattern.length > 0) {
        formatDate = DateFormatUtils.format(date, pattern[0].toString());
    } else {
        formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
    }
    return formatDate;
}


public String getDateTime(){
    return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
}


public String getTime(){
    return formatDate(new Date(), "HH:mm:ss");
}


public String formatDateTime(long timeMillis){
    long day = timeMillis / (24 * 60 * 60 * 1000);
    long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
    long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
    long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
    return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
}


public Date toDate(Date date,String pattern){
    if (date == null) {
        return null;
    }
    try {
        return parseDate(formatDate(date, pattern), pattern);
    } catch (ParseException e) {
        return null;
    }
}


public long pastDays(Date date){
    long t = new Date().getTime() - date.getTime();
    return t / (24 * 60 * 60 * 1000);
}


public void main(String[] args){
    System.out.println(parseDate("2010/3/6"));
    System.out.println(stringToDate("2010/3/6", "yyyy/MM/dd"));
// System.out.println(getDate("yyyy年MM月dd日 E"));
// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
// System.out.println(time/(24*60*60*1000));
}


public String getWeek(){
    return formatDate(new Date(), "E");
}


public String getDay(){
    return formatDate(new Date(), "dd");
}


public Date stringToDate(String str,String style){
    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.SIMPLIFIED_CHINESE);
    try {
        if (str == null || str.equals("")) {
            date = null;
        } else {
            date = sdf.parse(str);
        }
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return date;
}


public String getYear(){
    return formatDate(new Date(), "yyyy");
}


public Date parseDate(Object str){
    if (str == null) {
        return null;
    }
    try {
        return parseDate(str.toString(), parsePatterns);
    } catch (ParseException e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        return null;
    }
}


public long pastHour(Date date){
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 60 * 1000);
}


public long pastMinutes(Date date){
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 1000);
}


public String getDate(String pattern){
    return DateFormatUtils.format(new Date(), pattern);
}


public String getMonth(){
    return formatDate(new Date(), "MM");
}


public String dateToString(Date date,String style){
    SimpleDateFormat sdf = new SimpleDateFormat(style);
    if (date != null) {
        return sdf.format(date);
    }
    return null;
}


public double getDistanceOfTwoDate(Date before,Date after){
    long beforeTime = before.getTime();
    long afterTime = after.getTime();
    return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
}


}