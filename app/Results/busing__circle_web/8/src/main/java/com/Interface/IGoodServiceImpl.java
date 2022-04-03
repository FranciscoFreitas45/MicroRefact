package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IGoodService;
public class IGoodServiceImpl implements IGoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Map<String,Object> queryGood(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGood"))
    .queryParam("id",id)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}