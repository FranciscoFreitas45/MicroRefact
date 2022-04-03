package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.INewsContentService;
public class INewsContentServiceImpl implements INewsContentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public NewsContentEntity findByWebpageCode(String webpageCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByWebpageCode"))
    .queryParam("webpageCode",webpageCode)
;  NewsContentEntity aux = restTemplate.getForObject(builder.toUriString(), NewsContentEntity.class);

 return aux;
}


}