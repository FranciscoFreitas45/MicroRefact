package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.ClubService;
public class ClubServiceImpl implements ClubService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Optional<Club> getClubData(Integer clubId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClubData"))
    .queryParam("clubId",clubId)
;  Optional<Club> aux = restTemplate.getForObject(builder.toUriString(), Optional<Club>.class);

 return aux;
}


}