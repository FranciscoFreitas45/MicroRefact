package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.GameService;
public class GameServiceImpl implements GameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public GameDetail findGameById(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGameById"))
    .queryParam("id",id)
;  GameDetail aux = restTemplate.getForObject(builder.toUriString(), GameDetail.class);

 return aux;
}


public List<Game> findAllGame(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllGame"))
;  List<Game> aux = restTemplate.getForObject(builder.toUriString(), List<Game>.class);

 return aux;
}


}