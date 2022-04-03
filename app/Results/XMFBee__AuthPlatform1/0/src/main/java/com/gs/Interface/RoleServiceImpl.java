package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.RoleService;
public class RoleServiceImpl implements RoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://17";


public Role queryByName(String roleName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByName"))
    .queryParam("roleName",roleName)
;  Role aux = restTemplate.getForObject(builder.toUriString(), Role.class);

 return aux;
}


}