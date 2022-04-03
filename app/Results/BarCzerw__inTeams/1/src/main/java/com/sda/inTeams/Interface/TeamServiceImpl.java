package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TeamService;
public class TeamServiceImpl implements TeamService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Team> getTeamsContainingMember(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTeamsContainingMember"))
    .queryParam("userId",userId)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


public List<Team> getTeamsOwnedBy(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTeamsOwnedBy"))
    .queryParam("userId",userId)
;  List<Team> aux = restTemplate.getForObject(builder.toUriString(), List<Team>.class);

 return aux;
}


}