package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.SysService;
public class SysServiceImpl implements SysService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public User findtUserById(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findtUserById"))
    .queryParam("userId",userId)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}