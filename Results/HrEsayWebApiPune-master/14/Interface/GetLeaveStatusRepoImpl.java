import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetLeaveStatusRepoImpl implements GetLeaveStatusRepo{

 private RestTemplate restTemplate;

  String url = "http://8";


public List<GetLeaveStatus> getLeaveTrailByLeaveId(int leaveId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLeaveTrailByLeaveId"))
    .queryParam("leaveId",leaveId);
  List<GetLeaveStatus> aux = restTemplate.getForObject(builder.toUriString(), List<GetLeaveStatus>.class)

 return aux;
}


}