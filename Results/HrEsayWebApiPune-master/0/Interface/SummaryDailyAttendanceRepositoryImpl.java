import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SummaryDailyAttendanceRepositoryImpl implements SummaryDailyAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public SummaryDailyAttendance summaryDailyAttendanceList1(String month,String year,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/summaryDailyAttendanceList1"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empId",empId);
  SummaryDailyAttendance aux = restTemplate.getForObject(builder.toUriString(), SummaryDailyAttendance.class)

 return aux;
}


}