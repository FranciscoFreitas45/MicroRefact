package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.ArchivedOrderRepository;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.ArchivedOrder;

public class ArchivedOrderRepositoryImpl implements ArchivedOrderRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public  <S extends ArchivedOrder> List<S> saveAll(Iterable<S> var1){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("var1",var1)
;  List<S> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}