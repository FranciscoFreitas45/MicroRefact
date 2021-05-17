import java.util.List;
public class MonthWithOT {

 private  String month;

 private  List<TotalOT> otlist;

 private RestTemplate restTemplate = new RestTemplate();


public String getMonth(){
    return month;
}


public List<TotalOT> getOtlist(){
    return otlist;
}


public void setMonth(String month){
    this.month = month;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMonth"));

.queryParam("month",month);
restTemplate.put(builder.toUriString(),null);
}


}