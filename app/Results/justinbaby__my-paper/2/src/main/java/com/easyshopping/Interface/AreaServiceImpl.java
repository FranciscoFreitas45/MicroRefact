package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.AreaService;
public class AreaServiceImpl implements AreaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Area> findRoots(Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRoots"))
    .queryParam("count",count)
;  List<Area> aux = restTemplate.getForObject(builder.toUriString(), List<Area>.class);

 return aux;
}


}