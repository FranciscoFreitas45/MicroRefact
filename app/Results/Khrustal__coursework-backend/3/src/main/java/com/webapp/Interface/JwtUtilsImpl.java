package com.webapp.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.Interface.JwtUtils;
public class JwtUtilsImpl implements JwtUtils{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public boolean validateJwtToken(String authToken){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validateJwtToken"))
    .queryParam("authToken",authToken)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public String getUserNameFromJwtToken(String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserNameFromJwtToken"))
    .queryParam("token",token)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}