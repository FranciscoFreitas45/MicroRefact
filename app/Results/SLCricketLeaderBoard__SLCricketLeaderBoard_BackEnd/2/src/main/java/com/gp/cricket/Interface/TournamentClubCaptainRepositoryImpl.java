package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentClubCaptainRepository;
public class TournamentClubCaptainRepositoryImpl implements TournamentClubCaptainRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public TournamentClubCaptain findByTournamentClubId(TournamentClub tournamentClub){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTournamentClubId"))
    .queryParam("tournamentClub",tournamentClub)
;  TournamentClubCaptain aux = restTemplate.getForObject(builder.toUriString(), TournamentClubCaptain.class);

 return aux;
}


}