package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.MatchService;
public class MatchServiceImpl implements MatchService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Match> findMatchesByTournamentIdForCalender(Integer tournamentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMatchesByTournamentIdForCalender"))
    .queryParam("tournamentId",tournamentId)
;  List<Match> aux = restTemplate.getForObject(builder.toUriString(), List<Match>.class);

 return aux;
}


}