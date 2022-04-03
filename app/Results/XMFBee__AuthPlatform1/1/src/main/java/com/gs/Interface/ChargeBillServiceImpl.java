package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.ChargeBillService;
public class ChargeBillServiceImpl implements ChargeBillService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object queryById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}