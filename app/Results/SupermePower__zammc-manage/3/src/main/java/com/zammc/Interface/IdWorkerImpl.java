package com.zammc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zammc.Interface.IdWorker;
public class IdWorkerImpl implements IdWorker{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public long nextId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/nextId"))
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}