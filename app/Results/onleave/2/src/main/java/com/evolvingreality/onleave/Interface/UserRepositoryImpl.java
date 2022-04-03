package com.evolvingreality.onleave.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<User> findAllByGroupMembers_SecurityGroupGroupNameIn(Collection<String> groupNames){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByGroupMembers_SecurityGroupGroupNameIn"))
    .queryParam("groupNames",groupNames)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}