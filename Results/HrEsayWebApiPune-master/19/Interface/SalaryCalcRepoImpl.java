import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SalaryCalcRepoImpl implements SalaryCalcRepo{

 private RestTemplate restTemplate;

  String url = "http://11";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}