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


public long llength(RedisPrefixKey keyPrefix,String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/llength"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public T rpop(RedisPrefixKey keyPrefix,String key,Class<T> tClass){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/rpop"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("tClass",tClass)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public void del(RedisPrefixKey keyPrefix,String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/del"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
;
  restTemplate.put(builder.toUriString(), null);
}


}