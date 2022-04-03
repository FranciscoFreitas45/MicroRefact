package com.project.stockexchangeappbackend.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.Interface.StockIndexTimeProperties;

public class StockIndexTimePropertiesImpl implements StockIndexTimeProperties{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object getSystemResourcesMonitorHistory(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemResourcesMonitorHistory"))
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getSystemResourcesMonitorInterval(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemResourcesMonitorInterval"))
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}