import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DailyAttendanceRepositoryImpl implements DailyAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<DailyAttendance> dailyAttendanceListRec(String filterDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/dailyAttendanceListRec"))
    .queryParam("filterDate",filterDate);
  List<DailyAttendance> aux = restTemplate.getForObject(builder.toUriString(), List<DailyAttendance>.class)

 return aux;
}


public DailyAttendance dailyAttendanceListLastRec(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/dailyAttendanceListLastRec"))
  DailyAttendance aux = restTemplate.getForObject(builder.toUriString(), DailyAttendance.class)

 return aux;
}


}