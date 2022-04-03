package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.HttpService;
public class HttpServiceImpl implements HttpService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Http getName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
    .queryParam("name",name)
;  Http aux = restTemplate.getForObject(builder.toUriString(), Http.class);

 return aux;
}


}