package com.gs.common.util;
 import java.util.Calendar;
import java.util.Date;
public class DateUtil {

 public  String PAST_SECONDS;

 public  String PAST_MINUTES;

 public  String PAST_HOURS;

 public  String PAST_YESTERDAY;

 public  String PAST_DAYS;

 public  long SIXTY_SECONDS;

 public  long SIXTY_MINUTES;

 public  long TWENTY_FOUR_HOURS;

 public  long TWO_DAYS;

 public  long SEVEN_DAYS;


public String pastTime(Date past){
    return pastTime(past.getTime(), Calendar.getInstance().getTimeInMillis());
}


}