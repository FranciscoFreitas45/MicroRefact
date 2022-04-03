package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.GlobalSettingsRepository;
public class GlobalSettingsRepositoryImpl implements GlobalSettingsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String postPremoderation(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/postPremoderation"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object equals(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}