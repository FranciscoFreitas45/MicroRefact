package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.MatchRepository;
public class MatchRepositoryImpl implements MatchRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Match> findBytournamentId(Integer tournamentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBytournamentId"))
    .queryParam("tournamentId",tournamentId)
;  List<Match> aux = restTemplate.getForObject(builder.toUriString(), List<Match>.class);

 return aux;
}


}