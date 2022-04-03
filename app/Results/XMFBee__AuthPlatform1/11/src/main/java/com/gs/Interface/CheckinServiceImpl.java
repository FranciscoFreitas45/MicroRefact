package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.CheckinService;
public class CheckinServiceImpl implements CheckinService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Checkin> queryByPhone(String userPhone){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPhone"))
    .queryParam("userPhone",userPhone)
;  List<Checkin> aux = restTemplate.getForObject(builder.toUriString(), List<Checkin>.class);

 return aux;
}


public void updateUserIds(String userId,String chIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateUserIds"))
    .queryParam("userId",userId)
    .queryParam("chIds",chIds)
;
  restTemplate.put(builder.toUriString(), null);
}


}