package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.PlayerRepository;
public class PlayerRepositoryImpl implements PlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Player findPlayerById(Integer playerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayerById"))
    .queryParam("playerId",playerId)
;  Player aux = restTemplate.getForObject(builder.toUriString(), Player.class);

 return aux;
}


public Object existsById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}