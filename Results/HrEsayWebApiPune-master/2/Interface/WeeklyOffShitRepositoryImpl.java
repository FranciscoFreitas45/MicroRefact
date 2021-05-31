import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class WeeklyOffShitRepositoryImpl implements WeeklyOffShitRepository{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<WeeklyOffShit> getWeeklyOffShitList(String fromDate,String toDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeeklyOffShitList"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate);
  List<WeeklyOffShit> aux = restTemplate.getForObject(builder.toUriString(), List<WeeklyOffShit>.class)

 return aux;
}


public List<WeeklyOffShit> weeklyOffShitFromList(String fromDate,String toDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/weeklyOffShitFromList"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate);
  List<WeeklyOffShit> aux = restTemplate.getForObject(builder.toUriString(), List<WeeklyOffShit>.class)

 return aux;
}


public WeeklyOffShit shiftWeeklyofById(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/shiftWeeklyofById"))
    .queryParam("id",id);
  WeeklyOffShit aux = restTemplate.getForObject(builder.toUriString(), WeeklyOffShit.class)

 return aux;
}


}