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


public Long getNextID(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNextID"))
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public void add(ChatroomEntities newRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("newRecord",newRecord)
;
  restTemplate.put(builder.toUriString(), null);
}


public ChatroomEntities getRoomByName(String room_name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRoomByName"))
    .queryParam("room_name",room_name)
;  ChatroomEntities aux = restTemplate.getForObject(builder.toUriString(), ChatroomEntities.class);

 return aux;
}


public boolean delete(ChatroomEntities deleteRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("deleteRecord",deleteRecord)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void setChatroomEntities(String room_name,Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChatroomEntities"))
    .queryParam("room_name",room_name)
    .queryParam("room_id",room_id)
;
  restTemplate.put(builder.toUriString(), null);
}


public ChatroomEntities findByRoomID(Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoomID"))
    .queryParam("room_id",room_id)
;  ChatroomEntities aux = restTemplate.getForObject(builder.toUriString(), ChatroomEntities.class);

 return aux;
}


}