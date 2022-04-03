package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.UserDAO;
public class UserDAOImpl implements UserDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public User findByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmail"))
    .queryParam("email",email)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}