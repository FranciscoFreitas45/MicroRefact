package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.Logging;
public class LoggingImpl implements Logging{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void setTrace(String action,String comments){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTrace"))
    .queryParam("action",action)
    .queryParam("comments",comments)
;
  restTemplate.put(builder.toUriString(), null);
}


}