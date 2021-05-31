import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PayDeductionDetailsRepoImpl implements PayDeductionDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://20";


public int updatePayde(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updatePayde"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}