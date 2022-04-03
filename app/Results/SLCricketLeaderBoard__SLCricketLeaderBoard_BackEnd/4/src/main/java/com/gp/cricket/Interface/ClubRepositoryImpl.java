package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.ClubRepository;
public class ClubRepositoryImpl implements ClubRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Object existsById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Club findClubByClubId(Integer clubId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findClubByClubId"))
    .queryParam("clubId",clubId)
;  Club aux = restTemplate.getForObject(builder.toUriString(), Club.class);

 return aux;
}


}