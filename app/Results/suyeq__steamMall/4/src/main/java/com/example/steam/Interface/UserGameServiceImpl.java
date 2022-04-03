package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.UserGameService;
public class UserGameServiceImpl implements UserGameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public boolean isContains(String email,long gameId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isContains"))
    .queryParam("email",email)
    .queryParam("gameId",gameId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findThreeRecentGameVoListByEmail"))
    .queryParam("email",email)
;  List<RecentGameVo> aux = restTemplate.getForObject(builder.toUriString(), List<RecentGameVo>.class);

 return aux;
}


}