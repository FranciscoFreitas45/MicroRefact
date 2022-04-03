package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.MobileUserRepository;
public class MobileUserRepositoryImpl implements MobileUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public MobileUser findMobileUserByAccount(String account){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMobileUserByAccount"))
    .queryParam("account",account)
;  MobileUser aux = restTemplate.getForObject(builder.toUriString(), MobileUser.class);

 return aux;
}


}