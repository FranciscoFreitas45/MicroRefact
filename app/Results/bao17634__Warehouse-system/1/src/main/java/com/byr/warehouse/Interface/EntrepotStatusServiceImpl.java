package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.EntrepotStatusService;
public class EntrepotStatusServiceImpl implements EntrepotStatusService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public int getAllEntrepotCount(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllEntrepotCount"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}