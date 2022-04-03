package com.evolvingreality.onleave.domain;
 import java.time.DayOfWeek;
public class Day {

 private  DayOfWeek dayOfWeek;

 private  Integer dayOfMonth;

 private  Integer dayOfYear;

public Day(DayOfWeek dayOfWeek, Integer dayOfMonth, Integer dayOfYear) {
    this.dayOfWeek = dayOfWeek;
}
public DayOfWeek getDayOfWeek(){
    return dayOfWeek;
}


public Integer getDayOfYear(){
    return dayOfYear;
}


public Integer getDayOfMonth(){
    return dayOfMonth;
}


}