package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.EsClient;
public class EsClientImpl implements EsClient{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public TransportClient getTransportClient(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTransportClient"))
;  TransportClient aux = restTemplate.getForObject(builder.toUriString(), TransportClient.class);

 return aux;
}


}