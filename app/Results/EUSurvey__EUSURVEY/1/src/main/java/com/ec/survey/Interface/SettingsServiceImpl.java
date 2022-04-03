package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SettingsService;
public class SettingsServiceImpl implements SettingsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public String get(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("key",key)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}