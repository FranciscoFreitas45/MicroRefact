package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.LoginService;
public class LoginServiceImpl implements LoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void create(Login login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("login",login)
;
  restTemplate.put(builder.toUriString(), null);
}


}