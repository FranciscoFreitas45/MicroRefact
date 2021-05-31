import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SummaryDailyAttendanceRepositoryImpl implements SummaryDailyAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(int companyId,int empId,String fmonth,String fyear,String lmonth,String lyear){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByCompanyIdAndEmpId"))
    .queryParam("companyId",companyId)
    .queryParam("empId",empId)
    .queryParam("fmonth",fmonth)
    .queryParam("fyear",fyear)
    .queryParam("lmonth",lmonth)
    .queryParam("lyear",lyear);
  List<SummaryDailyAttendance> aux = restTemplate.getForObject(builder.toUriString(), List<SummaryDailyAttendance>.class)

 return aux;
}


public List<SummaryDailyAttendance> findAllByCompanyId(int locId,String fmonth,String fyear,String lmonth,String lyear){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByCompanyId"))
    .queryParam("locId",locId)
    .queryParam("fmonth",fmonth)
    .queryParam("fyear",fyear)
    .queryParam("lmonth",lmonth)
    .queryParam("lyear",lyear);
  List<SummaryDailyAttendance> aux = restTemplate.getForObject(builder.toUriString(), List<SummaryDailyAttendance>.class)

 return aux;
}


}