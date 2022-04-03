package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.LabelService;
public class LabelServiceImpl implements LabelService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public int deleteGameLabelByGameId(long gameId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteGameLabelByGameId"))
    .queryParam("gameId",gameId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<String> findLabelNamesByGameId(long gameId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLabelNamesByGameId"))
    .queryParam("gameId",gameId)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}