package pt.iscte.hospital.objects.utils;
 import java.time.LocalDate;
public class Day {

 private  Integer dayId;

 private  Integer dayNumber;

 private  String dateStr;

 private  LocalDate date;

 private  String color;

public Day(Integer dayId, Integer dayNumber, String dateStr, LocalDate date) {
    this.dayId = dayId;
    this.dayNumber = dayNumber;
    this.dateStr = dateStr;
    this.date = date;
    this.color = CalendarColor.WHITE.getName();
}
public void setColor(String color){
    this.color = color;
}


public Integer getDayNumber(){
    return dayNumber;
}


public String getDateStr(){
    return dateStr;
}


public String getColor(){
    return color;
}


public LocalDate getDate(){
    return date;
}


public Integer getDayId(){
    return dayId;
}


}