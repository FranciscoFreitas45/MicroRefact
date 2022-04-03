package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.AuthorityRepository;
public class AuthorityRepositoryImpl implements AuthorityRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Set<Resource> getRoleByAuthority(String roleId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRoleByAuthority"))
    .queryParam("roleId",roleId)
;  Set<Resource> aux = restTemplate.getForObject(builder.toUriString(), Set<Resource>.class);

 return aux;
}


}