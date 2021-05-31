import java.util.List;
public class DateAndDay {

 private  String date;

 private  String day;

 private  List<DailyAttendaceReport> finalDailyList;


public List<DailyAttendaceReport> getFinalDailyList(){
    return finalDailyList;
}


public void setDay(String day){
    this.day = day;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public void setFinalDailyList(List<DailyAttendaceReport> finalDailyList){
    this.finalDailyList = finalDailyList;
}


@Override
public String toString(){
    return "DateAndDay [date=" + date + ", day=" + day + ", finalDailyList=" + finalDailyList + "]";
}


public String getDay(){
    return day;
}


}