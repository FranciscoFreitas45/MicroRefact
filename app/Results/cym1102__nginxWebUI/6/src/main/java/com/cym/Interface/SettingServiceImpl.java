package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.SettingService;
public class SettingServiceImpl implements SettingService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public String get(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("key",key)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void set(String key,String value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("key",key)
    .queryParam("value",value)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object equals(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}