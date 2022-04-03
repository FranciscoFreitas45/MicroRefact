package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.RedisService;
public class RedisServiceImpl implements RedisService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPipelineBatch"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("keyList",keyList)
    .queryParam("clazz",clazz)
;  List<T> aux = restTemplate.getForObject(builder.toUriString(), List<T>.class);

 return aux;
}


}