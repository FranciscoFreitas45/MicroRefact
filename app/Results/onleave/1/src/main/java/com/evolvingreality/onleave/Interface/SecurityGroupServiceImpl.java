package com.evolvingreality.onleave.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.Interface.SecurityGroupService;
import java.util.*;
import com.evolvingreality.onleave.DTO.SecurityGroup;
public class SecurityGroupServiceImpl implements SecurityGroupService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Optional<SecurityGroup> get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Optional<SecurityGroup> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}