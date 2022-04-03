package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IMsgService;
public class IMsgServiceImpl implements IMsgService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public int queryCircleMsgCount(String sendUserId,String reciveUserId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCircleMsgCount"))
    .queryParam("sendUserId",sendUserId)
    .queryParam("reciveUserId",reciveUserId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}