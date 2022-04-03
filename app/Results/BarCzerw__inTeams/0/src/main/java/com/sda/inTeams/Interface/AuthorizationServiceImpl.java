package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.AuthorizationService;
public class AuthorizationServiceImpl implements AuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean isUserEligibleToSeeProjectDetails(Principal principal,Project project){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserEligibleToSeeProjectDetails"))
    .queryParam("principal",principal)
    .queryParam("project",project)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}