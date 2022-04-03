package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.SelectedPlayerService;
public class SelectedPlayerServiceImpl implements SelectedPlayerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public SelectedPlayer saveSelectedPlayer(SelectedPlayer player){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveSelectedPlayer"))
    .queryParam("player",player)
;  SelectedPlayer aux = restTemplate.getForObject(builder.toUriString(), SelectedPlayer.class);

 return aux;
}


}