package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IUserService;
public class IUserServiceImpl implements IUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public UserEntity findByUserName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserName"))
    .queryParam("userName",userName)
;  UserEntity aux = restTemplate.getForObject(builder.toUriString(), UserEntity.class);

 return aux;
}


}