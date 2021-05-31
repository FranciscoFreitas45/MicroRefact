import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetAuthorityIdsRepoImpl implements GetAuthorityIdsRepo{

 private RestTemplate restTemplate;

  String url = "http://4";


public GetAuthorityIds getAuthIds(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAuthIds"))
    .queryParam("empId",empId);
  GetAuthorityIds aux = restTemplate.getForObject(builder.toUriString(), GetAuthorityIds.class)

 return aux;
}


}