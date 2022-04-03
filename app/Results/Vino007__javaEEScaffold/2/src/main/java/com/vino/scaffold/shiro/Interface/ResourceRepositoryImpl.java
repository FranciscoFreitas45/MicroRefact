package com.vino.scaffold.shiro.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.Interface.ResourceRepository;
public class ResourceRepositoryImpl implements ResourceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Resource findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Resource aux = restTemplate.getForObject(builder.toUriString(), Resource.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void deleteAssociateById(Long resourceIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAssociateById"))
    .queryParam("resourceIds",resourceIds)
;
  restTemplate.put(builder.toUriString(), null);
}


}