import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LoanDetailsRepoImpl implements LoanDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://18";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}