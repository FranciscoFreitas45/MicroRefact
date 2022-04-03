package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.RoleRepository;
public class RoleRepositoryImpl implements RoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Optional<Role> findByRole(Roles role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRole"))
    .queryParam("role",role)
;  Optional<Role> aux = restTemplate.getForObject(builder.toUriString(), Optional<Role>.class);

 return aux;
}


public Object orElseThrow(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/orElseThrow"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}