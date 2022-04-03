package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public User getByLoginName(String loginName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByLoginName"))
    .queryParam("loginName",loginName)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}