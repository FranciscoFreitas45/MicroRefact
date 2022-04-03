package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.BannedAccessTokens;
public class BannedAccessTokensImpl implements BannedAccessTokens{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void addUser(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addUser"))
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


}