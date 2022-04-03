package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ILoginService;
public class ILoginServiceImpl implements ILoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public User login(String openid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/login"))
    .queryParam("openid",openid)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public boolean updateUserLastLoginTimerByWeixin(String openid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUserLastLoginTimerByWeixin"))
    .queryParam("openid",openid)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}