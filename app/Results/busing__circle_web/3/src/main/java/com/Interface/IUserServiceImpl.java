package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IUserService;
public class IUserServiceImpl implements IUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean addWeixinUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addWeixinUser"))
    .queryParam("user",user)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public User getUserByInviteCode(String inviteCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserByInviteCode"))
    .queryParam("inviteCode",inviteCode)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}