import java.time.LocalDate;
public class Day {

 private  Integer dayId;

 private  Integer dayNumber;

 private  String dateStr;

 private  LocalDate date;

 private  String color;


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