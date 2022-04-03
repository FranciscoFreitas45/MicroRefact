package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.RoleDao;
public class RoleDaoImpl implements RoleDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Role findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Role aux = restTemplate.getForObject(builder.toUriString(), Role.class);

 return aux;
}


}