package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentClubRepository;
public class TournamentClubRepositoryImpl implements TournamentClubRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Integer findIdByTournamentAndClub(Integer tournamentId,Integer clubId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findIdByTournamentAndClub"))
    .queryParam("tournamentId",tournamentId)
    .queryParam("clubId",clubId)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public TournamentClub findByClubIdAndTournamentId(Club clubId,Tournament tournamentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByClubIdAndTournamentId"))
    .queryParam("clubId",clubId)
    .queryParam("tournamentId",tournamentId)
;  TournamentClub aux = restTemplate.getForObject(builder.toUriString(), TournamentClub.class);

 return aux;
}


}