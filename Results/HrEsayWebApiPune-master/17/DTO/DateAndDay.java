import java.util.List;
public class DateAndDay {

 private  String date;

 private  String day;

 private  List<DailyAttendaceReport> finalDailyList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public List<DailyAttendaceReport> getFinalDailyList(){
    return finalDailyList;
}


public String getDate(){
    return date;
}


public String getDay(){
    return day;
}


public void setDate(String date){
    this.date = date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDate"));

.queryParam("date",date);
restTemplate.put(builder.toUriString(),null);
}


public void setDay(String day){
    this.day = day;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDay"));

.queryParam("day",day);
restTemplate.put(builder.toUriString(),null);
}


}