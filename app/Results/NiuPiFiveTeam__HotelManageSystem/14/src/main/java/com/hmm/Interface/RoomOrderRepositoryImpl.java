package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.RoomOrderRepository;
public class RoomOrderRepositoryImpl implements RoomOrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public Float findRoomOrderByDay(String dateString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRoomOrderByDay"))
    .queryParam("dateString",dateString)
;  Float aux = restTemplate.getForObject(builder.toUriString(), Float.class);

 return aux;
}


}