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


public void del(RedisPrefixKey keyPrefix,String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/del"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
;
  restTemplate.put(builder.toUriString(), null);
}


public long zrem(RedisPrefixKey keyPrefix,String key,T member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/zrem"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("member",member)
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public Double zincr(RedisPrefixKey keyPrefix,String key,T member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/zincr"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("member",member)
;  Double aux = restTemplate.getForObject(builder.toUriString(), Double.class);

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


public Set<T> zrange(RedisPrefixKey keyPrefix,String key,long start,long end,Class<T> tClass){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/zrange"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("start",start)
    .queryParam("end",end)
    .queryParam("tClass",tClass)
;  Set<T> aux = restTemplate.getForObject(builder.toUriString(), Set<T>.class);

 return aux;
}


public List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPipelineBatch"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("keyList",keyList)
    .queryParam("clazz",clazz)
;  List<T> aux = restTemplate.getForObject(builder.toUriString(), List<T>.class);

 return aux;
}


public Long incKey(RedisPrefixKey keyPrefix,String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/incKey"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public void zadd(RedisPrefixKey keyPrefix,String key,RankScoreValue<T> rank){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/zadd"))
    .queryParam("keyPrefix",keyPrefix)
    .queryParam("key",key)
    .queryParam("rank",rank)
;
  restTemplate.put(builder.toUriString(), null);
}


}