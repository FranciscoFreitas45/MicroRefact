import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ClaimHeaderRepoImpl implements ClaimHeaderRepo{

 private RestTemplate restTemplate;

  String url = "http://4";


public int updateClaim(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateClaim"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}