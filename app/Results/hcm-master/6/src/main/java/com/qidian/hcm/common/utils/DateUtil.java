package com.qidian.hcm.common.utils;
 import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
@Slf4j
public class DateUtil {

 public  String DATE_FORMAT_YMD;

 public  String DATE_FORMAT_YM;

 public  String DATE_FORMAT_Y_M_D;

 private  Locale LOCALE;

private DateUtil() {
}
public Date parseDate(String dateStr,String pattern){
    if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
        return null;
    }
    Date date;
    SimpleDateFormat sdf = new SimpleDateFormat(pattern, LOCALE);
    try {
        date = sdf.parse(dateStr);
    } catch (ParseException e) {
        log.error("格式化错误", e);
        return null;
    }
    return date;
}


public String convertDateToStr(Date date,String formatStr){
    if (Objects.isNull(date) || StringUtils.isBlank(formatStr)) {
        return null;
    }
    SimpleDateFormat format = new SimpleDateFormat(formatStr, LOCALE);
    return format.format(date);
}


public Number getMonthDays(String formatStr){
    Date currentDate = DateUtil.parseDate(formatStr, DateUtil.DATE_FORMAT_YM);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    calendar.set(Calendar.DATE, 1);
    calendar.roll(Calendar.DATE, -1);
    return calendar.get(Calendar.DATE);
}


}