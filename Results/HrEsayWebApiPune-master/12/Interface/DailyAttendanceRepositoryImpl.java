import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DailyAttendanceRepositoryImpl implements DailyAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<DailyAttendance> dailyAttendanceListAll1(int companyId,String fromDate,String toDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/dailyAttendanceListAll1"))
    .queryParam("companyId",companyId)
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate);
  List<DailyAttendance> aux = restTemplate.getForObject(builder.toUriString(), List<DailyAttendance>.class)

 return aux;
}


}