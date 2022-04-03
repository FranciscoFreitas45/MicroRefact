package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.MessageService;
public class MessageServiceImpl implements MessageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Long count(Member member,Boolean read){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("member",member)
    .queryParam("read",read)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


}