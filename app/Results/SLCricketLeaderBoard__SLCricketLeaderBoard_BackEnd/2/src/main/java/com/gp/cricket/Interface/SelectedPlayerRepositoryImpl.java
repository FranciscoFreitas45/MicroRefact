package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.SelectedPlayerRepository;
public class SelectedPlayerRepositoryImpl implements SelectedPlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Player> selectedPlayersForMatch(Integer matchId,Integer clubId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/selectedPlayersForMatch"))
    .queryParam("matchId",matchId)
    .queryParam("clubId",clubId)
;  List<Player> aux = restTemplate.getForObject(builder.toUriString(), List<Player>.class);

 return aux;
}


}