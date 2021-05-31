import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveTrailRepositoryImpl implements LeaveTrailRepository{

 private RestTemplate restTemplate;

  String url = "http://1";


public int deleteByLeaveId(int leaveId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByLeaveId"))
    .queryParam("leaveId",leaveId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}