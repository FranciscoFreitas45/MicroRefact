package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.Language;
public class LanguageImpl implements Language{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public String getName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getCode(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCode"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object equalsIgnoreCase(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equalsIgnoreCase"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object toLowerCase(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toLowerCase"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}