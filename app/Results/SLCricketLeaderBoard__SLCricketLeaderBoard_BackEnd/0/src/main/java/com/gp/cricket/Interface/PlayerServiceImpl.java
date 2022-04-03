package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.PlayerService;
public class PlayerServiceImpl implements PlayerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Player getPlayer(Integer playerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPlayer"))
    .queryParam("playerId",playerId)
;  Player aux = restTemplate.getForObject(builder.toUriString(), Player.class);

 return aux;
}


}