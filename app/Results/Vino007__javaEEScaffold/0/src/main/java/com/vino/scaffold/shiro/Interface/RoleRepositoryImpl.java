package com.vino.scaffold.shiro.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.Interface.RoleRepository;
public class RoleRepositoryImpl implements RoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}