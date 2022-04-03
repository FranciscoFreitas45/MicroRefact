package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.UserTokenDao;
public class UserTokenDaoImpl implements UserTokenDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public UserToken findByToken(String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByToken"))
    .queryParam("token",token)
;  UserToken aux = restTemplate.getForObject(builder.toUriString(), UserToken.class);

 return aux;
}


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void delete(String token){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("token",token)
;
  restTemplate.put(builder.toUriString(), null);
}


}