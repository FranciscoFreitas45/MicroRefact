package com.zis.common.util;
 import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
public class ZisUtils {


public Date stringToDate(String dateStr){
    Date date = stringToDate(dateStr, "yyyy-MM");
    if (date == null) {
        date = stringToDate(dateStr, "yyyy/MM");
    }
    return date;
}


public String getDateString(String format,Date date){
    return new SimpleDateFormat(format).format(date);
}


public Date youluNet(String dt,Logger logger){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
    try {
        return dateFormat.parse(dt);
    } catch (ParseException e) {
        logger.error("出版时间转换失败", e);
        throw new RuntimeException(e);
    }
}


public String convertGetRequestCHN(String str){
    try {
        return new String(str.getBytes("ISO-8859-1"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
        return str;
    }
}


public void sleepQuietly(int millis){
    try {
        Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
}


public Date format(String dt,Logger logger){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    try {
        return dateFormat.parse(dt);
    } catch (ParseException e) {
        logger.error("出版时间转换失败", e);
        throw new RuntimeException(e);
    }
}


public Timestamp getTS(String dateString,String format){
    try {
        return getTS(new SimpleDateFormat(format).parse(dateString));
    } catch (ParseException e) {
        return null;
    }
}


}