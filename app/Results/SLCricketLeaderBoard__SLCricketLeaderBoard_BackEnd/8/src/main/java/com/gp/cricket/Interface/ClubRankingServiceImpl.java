package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.ClubRankingService;
public class ClubRankingServiceImpl implements ClubRankingService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void createRankingObject(Club club){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createRankingObject"))
    .queryParam("club",club)
;
  restTemplate.put(builder.toUriString(), null);
}


}