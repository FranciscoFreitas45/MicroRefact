package com.Interface;

import com.DTO.RTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

public class RTableRepositoryImpl implements RTableRepository{
@Autowired
 private RestTemplate restTemplate;

  String url = "http://localhost:8087";


public RTable findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object);
  RTable aux = restTemplate.getForObject(builder.toUriString(), RTable.class);
    System.out.println(aux);
 return  aux;
}


}