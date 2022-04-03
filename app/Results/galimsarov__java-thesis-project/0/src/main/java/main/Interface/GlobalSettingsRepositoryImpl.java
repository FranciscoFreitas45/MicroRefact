package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.GlobalSettingsRepository;
public class GlobalSettingsRepositoryImpl implements GlobalSettingsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String multiUser(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/multiUser"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}