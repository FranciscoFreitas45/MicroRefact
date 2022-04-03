package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.DTO.*;
import java.util.*;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<User> findAllByTeamsContaining(Team team){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByTeamsContaining"))
    .queryParam("team",team)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


public Optional<User> findByUniqueInvitationId(String uniqueInvitationId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUniqueInvitationId"))
    .queryParam("uniqueInvitationId",uniqueInvitationId)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}

   public Optional<User> findByUsername (String username){
     UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("findByUsername",username)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
   }



}