package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.TagService;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Tag;
public class TagServiceImpl implements TagService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<Tag> getTag(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTag"))
    .queryParam("name",name)
;  Optional<Tag> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}