package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Player;
import com.gp.cricket.Request.PlayerRequest;
public class PlayerRequestImpl implements PlayerRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setPlayerId(Player playerId,Integer playerIdv2){
 restTemplate.put("http://1/TournamentClubPlayer/{id}/Player/setPlayerId",playerId,playerIdv2);
 return ;
}


public Player getPlayerId(Integer playerIdv2){
 Player aux = restTemplate.getForObject("http://1/TournamentClubPlayer/{id}/Player/getPlayerId",Player.class,playerIdv2);
return aux;
}


}