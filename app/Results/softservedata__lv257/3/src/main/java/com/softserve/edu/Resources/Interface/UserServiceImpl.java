package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public VerificationToken createVerificationTokenForUser(User user,String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createVerificationTokenForUser"))
    .queryParam("user",user)
    .queryParam("token",token)
;  VerificationToken aux = restTemplate.getForObject(builder.toUriString(), VerificationToken.class);

 return aux;
}


}