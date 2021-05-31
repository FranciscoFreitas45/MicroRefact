import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetDailyDailyRecordRepositoryImpl implements GetDailyDailyRecordRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<GetDailyDailyRecord> summaryDailyAttendanceListAll1(String fromDate,String toDate,int locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/summaryDailyAttendanceListAll1"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("locId",locId);
  List<GetDailyDailyRecord> aux = restTemplate.getForObject(builder.toUriString(), List<GetDailyDailyRecord>.class)

 return aux;
}


}