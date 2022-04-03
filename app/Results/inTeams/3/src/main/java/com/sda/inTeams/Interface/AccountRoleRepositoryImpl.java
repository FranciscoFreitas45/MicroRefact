package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.AccountRoleRepository;
public class AccountRoleRepositoryImpl implements AccountRoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<AccountRole> findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Optional<AccountRole> aux = restTemplate.getForObject(builder.toUriString(), Optional<AccountRole>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}