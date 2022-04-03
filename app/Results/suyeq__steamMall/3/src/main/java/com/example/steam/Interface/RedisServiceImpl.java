package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.RedisService;
public class RedisServiceImpl implements RedisService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void lpush(RedisPrefixKey keyPrefix,String key,T value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lpush"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("value",value)
;
  restTemplate.put(builder.toUriString(), null);
}


}