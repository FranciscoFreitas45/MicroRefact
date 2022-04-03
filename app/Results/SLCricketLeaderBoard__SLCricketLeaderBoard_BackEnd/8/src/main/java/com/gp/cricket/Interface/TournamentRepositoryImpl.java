package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentRepository;
public class TournamentRepositoryImpl implements TournamentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<Tournament> findOnGoingTournament(Date date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOnGoingTournament"))
    .queryParam("date",date)
;  List<Tournament> aux = restTemplate.getForObject(builder.toUriString(), List<Tournament>.class);

 return aux;
}


public List<Tournament> findUpcomingTournament(Date date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUpcomingTournament"))
    .queryParam("date",date)
;  List<Tournament> aux = restTemplate.getForObject(builder.toUriString(), List<Tournament>.class);

 return aux;
}


public List<Tournament> findPastTournament(Date date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPastTournament"))
    .queryParam("date",date)
;  List<Tournament> aux = restTemplate.getForObject(builder.toUriString(), List<Tournament>.class);

 return aux;
}


}