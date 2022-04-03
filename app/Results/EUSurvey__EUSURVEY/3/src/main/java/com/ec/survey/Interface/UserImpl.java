package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.User;
public class UserImpl implements User{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Integer getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public Map<GlobalPrivilege,Integer> getGlobalPrivileges(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getGlobalPrivileges"))
;  Map<GlobalPrivilege,Integer> aux = restTemplate.getForObject(builder.toUriString(), Map<GlobalPrivilege,Integer>.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}