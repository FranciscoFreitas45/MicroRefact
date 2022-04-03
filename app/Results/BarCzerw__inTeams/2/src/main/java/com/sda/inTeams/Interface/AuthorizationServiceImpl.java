package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.AuthorizationService;
public class AuthorizationServiceImpl implements AuthorizationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean isUserAdmin(Principal principal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUserAdmin"))
    .queryParam("principal",principal)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}