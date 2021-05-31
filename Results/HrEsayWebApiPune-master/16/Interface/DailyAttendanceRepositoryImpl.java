import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DailyAttendanceRepositoryImpl implements DailyAttendanceRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public int updateOTById(String otHr,int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateOTById"))
    .queryParam("otHr",otHr)
    .queryParam("id",id);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int updateOTByIdAndApprove(String otHr,int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateOTByIdAndApprove"))
    .queryParam("otHr",otHr)
    .queryParam("id",id);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}