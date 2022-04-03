package com.lingxiang2014.util;
 import org.joda.time.DateTime;
public class ChangeDate {


public DateTime getPrevYear(int year,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.plusYears(-1 * index);
    return now;
}


public DateTime getPrevMonth(int year,int month,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.withMonthOfYear(month);
    now = now.plusMonths(-1 * index);
    now = now.withTime(0, 0, 0, 0);
    now = now.withDayOfMonth(1);
    return now;
}


public DateTime getNextYear(int year,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.plusYears(1 * index);
    return now;
}


public DateTime getPrevDay(int year,int month,int day,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.withMonthOfYear(month);
    now = now.withDayOfMonth(day);
    now = now.plusDays(-1 * index);
    return now;
}


public DateTime getNextDay(int year,int month,int day,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.withMonthOfYear(month);
    now = now.withDayOfMonth(day);
    now = now.plusDays(index);
    return now;
}


public DateTime getThisYear(){
    DateTime now = new DateTime();
    ;
    return now;
}


public DateTime getThisMonth(){
    DateTime now = new DateTime();
    now = now.withTime(0, 0, 0, 0);
    now = now.withDayOfMonth(1);
    return now;
}


public DateTime getThisDay(){
    DateTime now = new DateTime();
    return now;
}


public DateTime getNextMonth(int year,int month,int index){
    DateTime now = new DateTime();
    now = now.withYear(year);
    now = now.withMonthOfYear(month);
    now = now.plusMonths(index);
    now = now.withTime(0, 0, 0, 0);
    now = now.withDayOfMonth(1);
    return now;
}


}