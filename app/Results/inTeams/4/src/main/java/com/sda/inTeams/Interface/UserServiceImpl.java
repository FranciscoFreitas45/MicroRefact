package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<User> getAllMembersOfTeam(long teamId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllMembersOfTeam"))
    .queryParam("teamId",teamId)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}