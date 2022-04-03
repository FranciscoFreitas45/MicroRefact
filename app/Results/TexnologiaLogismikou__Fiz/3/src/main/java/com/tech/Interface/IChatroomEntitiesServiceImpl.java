package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IChatroomEntitiesService;
public class IChatroomEntitiesServiceImpl implements IChatroomEntitiesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public boolean validateRoomnameExistance(String room_name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validateRoomnameExistance"))
    .queryParam("room_name",room_name)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public ChatroomEntities getRoomByName(String room_name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRoomByName"))
    .queryParam("room_name",room_name)
;  ChatroomEntities aux = restTemplate.getForObject(builder.toUriString(), ChatroomEntities.class);

 return aux;
}


}