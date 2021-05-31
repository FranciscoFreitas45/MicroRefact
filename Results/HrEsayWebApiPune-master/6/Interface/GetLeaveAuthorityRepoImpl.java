import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetLeaveAuthorityRepoImpl implements GetLeaveAuthorityRepo{

 private RestTemplate restTemplate;

  String url = "http://8";


public List<String> getEmpReportingName(String[] reportIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpReportingName"))
    .queryParam("reportIds",reportIds);
  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class)

 return aux;
}


}