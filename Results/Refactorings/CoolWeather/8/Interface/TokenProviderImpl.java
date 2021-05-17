import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class TokenProviderImpl implements TokenProvider{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public String createToken(Authentication authentication,boolean rememberMe){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("createToken"))
    .queryParam("authentication",authentication)
    .queryParam("rememberMe",rememberMe);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}