package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.RedisService;
public class RedisServiceImpl implements RedisService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("clazz",clazz)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public void set(RedisPrefixKey keyPrefix,String key,T value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("value",value)
;
  restTemplate.put(builder.toUriString(), null);
}


}