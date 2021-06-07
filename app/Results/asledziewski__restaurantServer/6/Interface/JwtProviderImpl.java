import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JwtProviderImpl implements JwtProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String generateJwtToken(Authentication authentication){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generateJwtToken"))
    .queryParam("authentication",authentication);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}