package com.metservice.kanban.utils;
 import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DateUtils {

 private  Logger LOGGER;

 public  DateTimeZone NEW_ZEALAND_TIME;

 public  DateTimeFormatter CONVENTIONAL_NEW_ZEALAND_DATE_FORMAT;

 public  DateTimeFormatter ISO_FORMAT;

 public  String DATE_FORMAT_STR;

private DateUtils() {
}
public String formatIsoDate(LocalDate date){
    return date.toString(ISO_FORMAT);
}


public LocalDate currentLocalDate(){
    return new LocalDate(NEW_ZEALAND_TIME);
}


public LocalDate parseDate(String startDate,LocalDate defaultValue){
    LocalDate start;
    try {
        if (StringUtils.isNotEmpty(startDate)) {
            start = LocalDate.parse(startDate);
        } else {
            start = defaultValue;
        }
    } catch (RuntimeException e) {
        LOGGER.warn("Cannot parse date {}", startDate);
        LOGGER.warn("Got exception: ", e);
        start = defaultValue;
    }
    return start;
}


public LocalDate parseIsoDate(String dateString){
    return ISO_FORMAT.parseDateTime(dateString).toLocalDate();
}


public String formatConventionalNewZealandDate(LocalDate date){
    return date.toString(CONVENTIONAL_NEW_ZEALAND_DATE_FORMAT);
}


public LocalDate parseConventionalNewZealandDate(String dateString){
    return CONVENTIONAL_NEW_ZEALAND_DATE_FORMAT.parseDateTime(dateString).toLocalDate();
}


}