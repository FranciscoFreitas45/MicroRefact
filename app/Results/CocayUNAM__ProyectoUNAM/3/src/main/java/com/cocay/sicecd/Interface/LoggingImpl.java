package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.Logging;
public class LoggingImpl implements Logging{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario)
;
  restTemplate.put(builder.toUriString(), null);
}


}