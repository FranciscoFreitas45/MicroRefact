package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.JwtTokenProvider;
public class JwtTokenProviderImpl implements JwtTokenProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public String generateToken(Authentication authentication){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generateToken"))
    .queryParam("authentication",authentication)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}