package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.ConfigManager;
public class ConfigManagerImpl implements ConfigManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String getSiteName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSiteName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object replace(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/replace"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}