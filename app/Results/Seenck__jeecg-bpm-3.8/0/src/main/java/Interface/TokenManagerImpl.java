package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TokenManager;
public class TokenManagerImpl implements TokenManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://18";


public String createToken(TSUser user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createToken"))
    .queryParam("user",user)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void deleteToken(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteToken"))
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


}