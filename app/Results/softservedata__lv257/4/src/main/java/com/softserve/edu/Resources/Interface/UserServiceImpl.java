package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public User getUser(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUser"))
    .queryParam("username",username)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}