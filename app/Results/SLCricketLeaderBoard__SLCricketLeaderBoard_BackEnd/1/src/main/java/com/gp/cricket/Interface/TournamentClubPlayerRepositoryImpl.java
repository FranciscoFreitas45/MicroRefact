package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
public class TournamentClubPlayerRepositoryImpl implements TournamentClubPlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<TournamentClubPlayer> findPlayerTournamentStatus(Player playerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayerTournamentStatus"))
    .queryParam("playerId",playerId)
;  List<TournamentClubPlayer> aux = restTemplate.getForObject(builder.toUriString(), List<TournamentClubPlayer>.class);

 return aux;
}


}