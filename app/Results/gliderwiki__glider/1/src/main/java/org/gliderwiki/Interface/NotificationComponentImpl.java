package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.NotificationComponent;
public class NotificationComponentImpl implements NotificationComponent{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void sendNotification(Integer userIdx,String message){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendNotification"))
    .queryParam("userIdx",userIdx)
    .queryParam("message",message)
;
  restTemplate.put(builder.toUriString(), null);
}


}