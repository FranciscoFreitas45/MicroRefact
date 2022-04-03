package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.PlayerRepository;
public class PlayerRepositoryImpl implements PlayerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<String> findPlayerByClubId(Club clubId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPlayerByClubId"))
    .queryParam("clubId",clubId)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}