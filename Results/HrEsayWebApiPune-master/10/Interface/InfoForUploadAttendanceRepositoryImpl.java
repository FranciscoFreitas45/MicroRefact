import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class InfoForUploadAttendanceRepositoryImpl implements InfoForUploadAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public InfoForUploadAttendance getInformationOfUploadedShiftAssign(String fromDate,String toDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getInformationOfUploadedShiftAssign"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate);
  InfoForUploadAttendance aux = restTemplate.getForObject(builder.toUriString(), InfoForUploadAttendance.class)

 return aux;
}


}