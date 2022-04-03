package com.tech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.Interface.IUserService;
public class IUserServiceImpl implements IUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public boolean checkUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkUsername"))
    .queryParam("username",username)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public User getUserByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserByUsername"))
    .queryParam("username",username)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}