package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ICircleService;
public class ICircleServiceImpl implements ICircleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Circle queryCircleByCircleId(String circleId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCircleByCircleId"))
    .queryParam("circleId",circleId)
;  Circle aux = restTemplate.getForObject(builder.toUriString(), Circle.class);

 return aux;
}


}