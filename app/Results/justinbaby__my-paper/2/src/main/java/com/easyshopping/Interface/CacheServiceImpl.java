package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CacheService;
public class CacheServiceImpl implements CacheService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void clear(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
;
  restTemplate.put(builder.toUriString(), null);
}


}