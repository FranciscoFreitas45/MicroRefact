package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.TeamService;
public class TeamServiceImpl implements TeamService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Team getByIdOrThrow(long teamId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByIdOrThrow"))
    .queryParam("teamId",teamId)
;  Team aux = restTemplate.getForObject(builder.toUriString(), Team.class);

 return aux;
}


}