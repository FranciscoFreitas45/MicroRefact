package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
public class ResourceRepositoryImpl implements ResourceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<Resource> findAll(Specification<Resource> var1,Pageable var2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("var1",var1)
    .queryParam("var2",var2)
;  Page<Resource> aux = restTemplate.getForObject(builder.toUriString(), Page<Resource>.class);

 return aux;
}


public Optional<Resource> findByUserAndStock(User user,Stock stock){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserAndStock"))
    .queryParam("user",user)
    .queryParam("stock",stock)
;  Optional<Resource> aux = restTemplate.getForObject(builder.toUriString(), Optional<Resource>.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public S save(S s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("s",s)
;  S aux = restTemplate.getForObject(builder.toUriString(), S.class);

 return aux;
}


}