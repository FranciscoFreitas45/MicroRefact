import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveEncashRepositoryImpl implements LeaveEncashRepository{

 private RestTemplate restTemplate;

  String url = "http://1";


public int updateEncashAmt(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateEncashAmt"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}