package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.SeoService;
public class SeoServiceImpl implements SeoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public Seo find(Type type,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("type",type)
    .queryParam("cacheRegion",cacheRegion)
;  Seo aux = restTemplate.getForObject(builder.toUriString(), Seo.class);

 return aux;
}


}