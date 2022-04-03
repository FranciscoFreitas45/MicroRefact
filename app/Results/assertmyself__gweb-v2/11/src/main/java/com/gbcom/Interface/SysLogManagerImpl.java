package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysLogManager;
public class SysLogManagerImpl implements SysLogManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void log(String username,String pageUrl,String userIp,String sessionId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/log"))
    .queryParam("username",username)
    .queryParam("pageUrl",pageUrl)
    .queryParam("userIp",userIp)
    .queryParam("sessionId",sessionId)
;
  restTemplate.put(builder.toUriString(), null);
}


}