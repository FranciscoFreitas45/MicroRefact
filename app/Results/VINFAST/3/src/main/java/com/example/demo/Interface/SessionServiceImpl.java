package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.SessionService;
public class SessionServiceImpl implements SessionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void remove(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/remove"))
    .queryParam("name",name)
;
  restTemplate.put(builder.toUriString(), null);
}


public void set(String name,Object value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("name",name)
    .queryParam("value",value)
;
  restTemplate.put(builder.toUriString(), null);
}


}