package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.ICRLocationService;
public class ICRLocationServiceImpl implements ICRLocationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void add(ChatroomLocation saveRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))
    .queryParam("saveRecord",saveRecord)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean checkIfStillInside(Long room_id,float lng,float lat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkIfStillInside"))
    .queryParam("room_id",room_id)
    .queryParam("lng",lng)
    .queryParam("lat",lat)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void setNewMaxRange(int room_max_range,Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewMaxRange"))
    .queryParam("room_max_range",room_max_range)
    .queryParam("room_id",room_id)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<ChatroomLocation> findIfNear(float lng,float lat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findIfNear"))
    .queryParam("lng",lng)
    .queryParam("lat",lat)
;  List<ChatroomLocation> aux = restTemplate.getForObject(builder.toUriString(), List<ChatroomLocation>.class);

 return aux;
}


public void setNewLngLat(float lng,float lat,Long room_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewLngLat"))
    .queryParam("lng",lng)
    .queryParam("lat",lat)
    .queryParam("room_id",room_id)
;
  restTemplate.put(builder.toUriString(), null);
}


}