package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.StaticService;
public class StaticServiceImpl implements StaticService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public int buildIndex(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildIndex"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int buildOther(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/buildOther"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}