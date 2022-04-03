package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.LocalStoreService;
public class LocalStoreServiceImpl implements LocalStoreService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public T get(LocalStoreKey key,Class<T> tClass,String page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("key",key)
    .queryParam("tClass",tClass)
    .queryParam("page",page)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public void set(LocalStoreKey key,T value,String page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("key",key)
    .queryParam("value",value)
    .queryParam("page",page)
;
  restTemplate.put(builder.toUriString(), null);
}


}