package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.TournamentService;
public class TournamentServiceImpl implements TournamentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<Tournament> getTournamentsByDateOrder(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTournamentsByDateOrder"))
;  List<Tournament> aux = restTemplate.getForObject(builder.toUriString(), List<Tournament>.class);

 return aux;
}


public List<Tournament> getTournamentsHistory(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTournamentsHistory"))
;  List<Tournament> aux = restTemplate.getForObject(builder.toUriString(), List<Tournament>.class);

 return aux;
}


}