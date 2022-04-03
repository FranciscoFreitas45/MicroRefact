package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.RedisUtils;
public class RedisUtilsImpl implements RedisUtils{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object get(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("key",key)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public boolean set(String key,Object value,long time){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("key",key)
    .queryParam("value",value)
    .queryParam("time",time)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}