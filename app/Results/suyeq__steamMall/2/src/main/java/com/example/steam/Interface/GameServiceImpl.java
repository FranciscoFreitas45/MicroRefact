package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.GameService;
public class GameServiceImpl implements GameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public int updateGamePosterImage(long gameId,long posterImage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateGamePosterImage"))
    .queryParam("gameId",gameId)
    .queryParam("posterImage",posterImage)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}