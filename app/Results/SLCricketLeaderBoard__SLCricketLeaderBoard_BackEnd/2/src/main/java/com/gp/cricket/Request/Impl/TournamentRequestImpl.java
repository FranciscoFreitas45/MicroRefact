package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Tournament;
import com.gp.cricket.Request.TournamentRequest;
public class TournamentRequestImpl implements TournamentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Tournament getTournamentId(Integer tournamentIdv2){
 Tournament aux = restTemplate.getForObject("http://10/Match/{id}/Tournament/getTournamentId",Tournament.class,tournamentIdv2);
return aux;
}


public void setTournamentId(Tournament tournamentId,Integer tournamentIdv2){
 restTemplate.put("http://10/Match/{id}/Tournament/setTournamentId",tournamentId,tournamentIdv2);
 return ;
}


}