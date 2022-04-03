package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.ClubRepository;
public class ClubRepositoryImpl implements ClubRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Club findClubByManagerId(Integer managerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findClubByManagerId"))
    .queryParam("managerId",managerId)
;  Club aux = restTemplate.getForObject(builder.toUriString(), Club.class);

 return aux;
}


}