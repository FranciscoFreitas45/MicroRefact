package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ICircleService;
public class ICircleServiceImpl implements ICircleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Circle> queryCirclesByUserId(Object userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCirclesByUserId"))
    .queryParam("userId",userId)
;  List<Circle> aux = restTemplate.getForObject(builder.toUriString(), List<Circle>.class);

 return aux;
}


public int addCicle(Circle circle){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addCicle"))
    .queryParam("circle",circle)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Circle queryCircleByCircleId(String circleId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCircleByCircleId"))
    .queryParam("circleId",circleId)
;  Circle aux = restTemplate.getForObject(builder.toUriString(), Circle.class);

 return aux;
}


public Integer memberExitCircle(Circle cirlce,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/memberExitCircle"))
    .queryParam("cirlce",cirlce)
    .queryParam("user",user)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public Circle queryCircleAndUserByCircleId(Map<String,Object> paramMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCircleAndUserByCircleId"))
    .queryParam("paramMap",paramMap)
;  Circle aux = restTemplate.getForObject(builder.toUriString(), Circle.class);

 return aux;
}


public List<User> queryUsersByCircleId(Map<String,Object> paramMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryUsersByCircleId"))
    .queryParam("paramMap",paramMap)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public Integer saveCircleInfo(Map<String,Object> paramMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCircleInfo"))
    .queryParam("paramMap",paramMap)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public Integer saveCirclePublishInfo(Circle circle,Circle _circle,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCirclePublishInfo"))
    .queryParam("circle",circle)
    .queryParam("_circle",_circle)
    .queryParam("user",user)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}