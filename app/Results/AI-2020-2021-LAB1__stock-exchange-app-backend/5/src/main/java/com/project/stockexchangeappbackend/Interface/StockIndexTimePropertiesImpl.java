package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockIndexTimeProperties;
public class StockIndexTimePropertiesImpl implements StockIndexTimeProperties{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object getSystemResourcesMonitorHistory(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemResourcesMonitorHistory"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getSystemResourcesMonitorInterval(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemResourcesMonitorInterval"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}