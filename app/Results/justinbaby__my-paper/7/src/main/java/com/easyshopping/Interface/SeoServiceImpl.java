package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.SeoService;
public class SeoServiceImpl implements SeoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Seo find(Type type,String cacheRegion){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("type",type)
    .queryParam("cacheRegion",cacheRegion)
;  Seo aux = restTemplate.getForObject(builder.toUriString(), Seo.class);

 return aux;
}


}