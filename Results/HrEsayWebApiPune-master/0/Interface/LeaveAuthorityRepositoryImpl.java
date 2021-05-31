import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveAuthorityRepositoryImpl implements LeaveAuthorityRepository{

 private RestTemplate restTemplate;

  String url = "http://13";


public List<LeaveAuthority> chkAuth(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/chkAuth"))
    .queryParam("empId",empId);
  List<LeaveAuthority> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveAuthority>.class)

 return aux;
}


}