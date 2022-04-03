package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IWebsiteDicService;
public class IWebsiteDicServiceImpl implements IWebsiteDicService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WebsiteDicEntity findById(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  WebsiteDicEntity aux = restTemplate.getForObject(builder.toUriString(), WebsiteDicEntity.class);

 return aux;
}


}