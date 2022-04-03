package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public User registerUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/registerUser"))
    .queryParam("user",user)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}