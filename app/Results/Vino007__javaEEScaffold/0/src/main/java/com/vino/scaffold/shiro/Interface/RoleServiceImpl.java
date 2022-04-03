package com.vino.scaffold.shiro.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.Interface.RoleService;
public class RoleServiceImpl implements RoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}