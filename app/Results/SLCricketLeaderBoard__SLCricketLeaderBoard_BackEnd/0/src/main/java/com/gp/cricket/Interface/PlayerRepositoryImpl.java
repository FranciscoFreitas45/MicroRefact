package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.PlayerRepository;
public class PlayerRepositoryImpl implements PlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Player> findRemainingBatmenPlayers(Club clubId,Integer playerType,Integer matchTypeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRemainingBatmenPlayers"))
    .queryParam("clubId",clubId)
    .queryParam("playerType",playerType)
    .queryParam("matchTypeId",matchTypeId)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


public List<Player> findRemainingBallerPlayers(Club club,Integer playerType,Integer matchTypeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRemainingBallerPlayers"))
    .queryParam("club",club)
    .queryParam("playerType",playerType)
    .queryParam("matchTypeId",matchTypeId)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


}