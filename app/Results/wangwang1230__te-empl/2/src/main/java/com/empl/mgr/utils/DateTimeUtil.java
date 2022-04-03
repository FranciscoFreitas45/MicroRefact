package com.empl.mgr.utils;
 import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
public class DateTimeUtil {


public Date getCurrentTime(){
    return new Date();
}


public String conversionTime(Date date,String format){
    if (CompareUtil.isEmpty(date) || StringUtils.isEmpty(format))
        return "";
    return new SimpleDateFormat(format).format(date);
}


}