package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.ParentService;
public class ParentServiceImpl implements ParentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void create(Parent parent){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("parent",parent)
;
  restTemplate.put(builder.toUriString(), null);
}


}