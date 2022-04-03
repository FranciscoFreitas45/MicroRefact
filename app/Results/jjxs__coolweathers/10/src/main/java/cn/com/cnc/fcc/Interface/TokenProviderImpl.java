package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.TokenProvider;
public class TokenProviderImpl implements TokenProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String createToken(Authentication authentication,boolean rememberMe){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createToken"))
    .queryParam("authentication",authentication)
    .queryParam("rememberMe",rememberMe)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}