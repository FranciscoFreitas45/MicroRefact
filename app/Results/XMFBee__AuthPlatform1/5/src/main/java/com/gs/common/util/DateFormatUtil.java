package com.gs.common.util;
 import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class DateFormatUtil {

 public  String DEFAULT_PATTERN;

 public  String YEARFORMATER;

 public  String MONTHFORMTER;

 public  String DAYFORMATER;

 public  String MONTH;

 public  String WEEK;

 public  Map<String,ThreadLocal<DateFormat>> dfMap;


public String MonthFormater(Date date){
    return format(date, MONTHFORMTER);
}


public String MONTH(Date date){
    return format(date, MONTH);
}


public String YearFormater(Date date){
    return format(date, YEARFORMATER);
}


public String format(Date date,String pattern){
    return getDateFormat(pattern).format(date);
}


public String DayFormater(Date date){
    return format(date, DAYFORMATER);
}


public DateFormat getDateFormat(String pattern){
    ThreadLocal<DateFormat> dfThreadLocal = dfMap.get(pattern);
    if (dfThreadLocal == null) {
        synchronized (DateFormatUtil.class) {
            dfThreadLocal = new ThreadLocal<DateFormat>() {

                @Override
                protected DateFormat initialValue() {
                    return new SimpleDateFormat(pattern);
                }
            };
            dfMap.put(pattern, dfThreadLocal);
        }
    }
    return dfThreadLocal.get();
}


public String WEEK(Date date){
    return format(date, WEEK);
}


@Override
public DateFormat initialValue(){
    return new SimpleDateFormat(pattern);
}


public String defaultFormat(long millis){
    return format(millis, DEFAULT_PATTERN);
}


}