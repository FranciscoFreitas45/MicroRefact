package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void logout(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/logout"))
;
  restTemplate.put(builder.toUriString(), null);
}


}