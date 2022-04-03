package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.ICRLocationService;
public class ICRLocationServiceImpl implements ICRLocationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public boolean checkIfStillInside(Long room_id,float lng,float lat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkIfStillInside"))
    .queryParam("room_id",room_id)
    .queryParam("lng",lng)
    .queryParam("lat",lat)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}