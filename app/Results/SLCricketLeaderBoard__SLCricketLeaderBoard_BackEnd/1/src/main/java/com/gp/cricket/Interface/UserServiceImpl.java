package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public User signupUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/signupUser"))
    .queryParam("user",user)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public Integer updateUser(User updateUser){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUser"))
    .queryParam("updateUser",updateUser)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public Integer userAccountDeactivate(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/userAccountDeactivate"))
    .queryParam("userId",userId)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}