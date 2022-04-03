package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.GameService;
public class GameServiceImpl implements GameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Game findOneGameById(long id,String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneGameById"))
    .queryParam("id",id)
    .queryParam("key",key)
;  Game aux = restTemplate.getForObject(builder.toUriString(), Game.class);

 return aux;
}


}