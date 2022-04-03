package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.GroupRepository;
public class GroupRepositoryImpl implements GroupRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}