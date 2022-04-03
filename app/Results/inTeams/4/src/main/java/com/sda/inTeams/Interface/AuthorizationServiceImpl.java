package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.AuthorizationService;
public class AuthorizationServiceImpl implements AuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<User> getUserCredentials(Principal principal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserCredentials"))
    .queryParam("principal",principal)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional<User>.class);

 return aux;
}


public boolean isUserEligibleToSeeTeamDetails(Principal principal,Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToSeeTeamDetails"))
    .queryParam("principal",principal)
    .queryParam("team",team)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Object isUserEligibleToManageTeam(Principal principal,Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToManageTeam"))
    .queryParam("principal",principal)
    .queryParam("team",team)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}