package com.evolvingreality.onleave.service.util;
 import org.joda.time.DateTime;
public class DateTimeUtil {


public DateTime getStartOfYear(Integer year){
    return new DateTime(year, 1, 1, 0, 0, 0);
}


public DateTime getEndOfYear(Integer year){
    return new DateTime(year, 12, 31, 23, 59, 59);
}


}