package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.AuthConfiguration;
public class AuthConfigurationImpl implements AuthConfiguration{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Map<String,Integer> getAuths(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAuths"))
;  Map<String,Integer> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Integer>.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}