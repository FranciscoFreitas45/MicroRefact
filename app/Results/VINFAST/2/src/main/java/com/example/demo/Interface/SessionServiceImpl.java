package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.SessionService;
public class SessionServiceImpl implements SessionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public T get(String name,T defaultValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("name",name)
    .queryParam("defaultValue",defaultValue)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}