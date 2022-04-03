package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.LdapService;
public class LdapServiceImpl implements LdapService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<String> getUserLDAPGroups(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserLDAPGroups"))
    .queryParam("username",username)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}