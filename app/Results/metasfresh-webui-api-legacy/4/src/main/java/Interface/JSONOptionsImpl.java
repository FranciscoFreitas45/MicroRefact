package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JSONOptionsImpl implements JSONOptions{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public JSONOptions of(UserSession userSession){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("userSession",userSession);
  JSONOptions aux = restTemplate.getForObject(builder.toUriString(), JSONOptions.class);

 return aux;
}


}