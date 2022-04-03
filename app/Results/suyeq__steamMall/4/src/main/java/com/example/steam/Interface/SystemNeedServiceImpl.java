package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.SystemNeedService;
public class SystemNeedServiceImpl implements SystemNeedService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public long addSystemNeed(SystemNeed systemNeed){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addSystemNeed"))
    .queryParam("systemNeed",systemNeed)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}