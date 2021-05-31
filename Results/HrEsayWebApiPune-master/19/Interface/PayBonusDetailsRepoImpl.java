import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PayBonusDetailsRepoImpl implements PayBonusDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://11";


public int updateBonus(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateBonus"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}