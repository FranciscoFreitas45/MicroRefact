package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.SelectedPlayer;
import com.gp.cricket.Request.SelectedPlayerRequest;
public class SelectedPlayerRequestImpl implements SelectedPlayerRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setSelectedPlayerId(SelectedPlayer selectedPlayerId,Integer selectedPlayerIdv2){
 restTemplate.put("http://1/BallerRecord/{id}/SelectedPlayer/setSelectedPlayerId",selectedPlayerId,selectedPlayerIdv2);
 return ;
}


public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2){
 SelectedPlayer aux = restTemplate.getForObject("http://1/BallerRecord/{id}/SelectedPlayer/getSelectedPlayerId",SelectedPlayer.class,selectedPlayerIdv2);
return aux;
}


}