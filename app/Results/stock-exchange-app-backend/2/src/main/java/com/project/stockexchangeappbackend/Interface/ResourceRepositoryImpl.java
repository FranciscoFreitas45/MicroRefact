package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;
import com.project.stockexchangeappbackend.DTO.Resource;
import com.project.stockexchangeappbackend.DTO.User;
import com.project.stockexchangeappbackend.DTO.Stock;
import java.util.*;
public class ResourceRepositoryImpl implements ResourceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<Resource> findAll(Specification<Resource> var1,Pageable var2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("var1",var1)
    .queryParam("var2",var2)
;  Page<Resource> aux = restTemplate.getForObject(builder.toUriString(), Page.class);

 return aux;
}


public Optional<Resource> findByUserAndStock(User user,Stock stock){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserAndStock"))
    .queryParam("user",user)
    .queryParam("stock",stock)
;  Optional<Resource> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public <S extends Resource> S save(S s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("s",s)
;  S aux = restTemplate.getForObject(builder.toUriString(), S.class);

 return aux;
}


}