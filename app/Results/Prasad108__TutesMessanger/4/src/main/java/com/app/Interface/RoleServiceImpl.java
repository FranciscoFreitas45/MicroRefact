package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.RoleService;
public class RoleServiceImpl implements RoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Role findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Role aux = restTemplate.getForObject(builder.toUriString(), Role.class);

 return aux;
}


}