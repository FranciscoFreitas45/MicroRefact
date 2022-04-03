package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.AllOrdersRepository;
public class AllOrdersRepositoryImpl implements AllOrdersRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Long countByUser(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByUser"))
    .queryParam("user",user)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Object equals(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}