package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Match;
import com.gp.cricket.Request.MatchRequest;
public class MatchRequestImpl implements MatchRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Match getMatchId(Integer matchIdv2){
 Match aux = restTemplate.getForObject("http://2/SelectedPlayer/{id}/Match/getMatchId",Match.class,matchIdv2);
return aux;
}


public void setMatchId(Match matchId,Integer matchIdv2){
 restTemplate.put("http://2/SelectedPlayer/{id}/Match/setMatchId",matchId,matchIdv2);
 return ;
}


}