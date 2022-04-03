package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OneTimeTokenService;
public class OneTimeTokenServiceImpl implements OneTimeTokenService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Optional<String> get(String oneTimeToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("oneTimeToken",oneTimeToken)
;  Optional<String> aux = restTemplate.getForObject(builder.toUriString(), Optional<String>.class);

 return aux;
}


public void revoke(String oneTimeToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/revoke"))
    .queryParam("oneTimeToken",oneTimeToken)
;
  restTemplate.put(builder.toUriString(), null);
}


}