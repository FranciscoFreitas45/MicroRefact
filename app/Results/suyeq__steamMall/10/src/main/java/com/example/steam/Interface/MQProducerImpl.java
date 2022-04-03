package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.MQProducer;
public class MQProducerImpl implements MQProducer{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void productEvent(Event event){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/productEvent"))
    .queryParam("event",event)
;
  restTemplate.put(builder.toUriString(), null);
}


}