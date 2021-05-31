import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AttendanceApiControllerchangeImpl implements AttendanceApiControllerchange{

 private RestTemplate restTemplate;

  String url = "http://2";


public Info getVariousListForUploadAttendace(DataForUpdateAttendance dataForUpdateAttendance){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getVariousListForUploadAttendace"))
    .queryParam("dataForUpdateAttendance",dataForUpdateAttendance);
  Info aux = restTemplate.getForObject(builder.toUriString(), Info.class)

 return aux;
}


}