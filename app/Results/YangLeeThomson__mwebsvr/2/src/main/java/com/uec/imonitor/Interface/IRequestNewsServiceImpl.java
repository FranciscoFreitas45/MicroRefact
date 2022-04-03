package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IRequestNewsService;
public class IRequestNewsServiceImpl implements IRequestNewsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public RequestNewsEntity saveRequestNews(RequestNewsEntity entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveRequestNews"))
    .queryParam("entity",entity)
;  RequestNewsEntity aux = restTemplate.getForObject(builder.toUriString(), RequestNewsEntity.class);

 return aux;
}


}