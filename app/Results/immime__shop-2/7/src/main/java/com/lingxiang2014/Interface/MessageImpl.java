package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.Message;
public class MessageImpl implements Message{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Message error(String content,Object args){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/error"))
    .queryParam("content",content)
    .queryParam("args",args)
;  Message aux = restTemplate.getForObject(builder.toUriString(), Message.class);

 return aux;
}


public Message success(String content,Object args){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/success"))
    .queryParam("content",content)
    .queryParam("args",args)
;  Message aux = restTemplate.getForObject(builder.toUriString(), Message.class);

 return aux;
}


}