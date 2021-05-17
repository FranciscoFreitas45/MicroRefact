import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhOrderInfoServiceImpl implements PhOrderInfoService{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public String generateOrderNo(String prefix){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("generateOrderNo"))
    .queryParam("prefix",prefix);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}