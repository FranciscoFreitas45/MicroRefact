package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.BasicService;
public class BasicServiceImpl implements BasicService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public boolean contain(String content){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/contain"))
    .queryParam("content",content)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}