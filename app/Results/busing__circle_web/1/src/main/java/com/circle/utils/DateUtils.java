package com.circle.utils;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtils {


public String getEndTime(String issue_time){
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(issue_time);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, date.getDate() - 1);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return sdf.format(cal.getTime());
    } catch (ParseException e) {
        e.printStackTrace();
        return "";
    }
}


}