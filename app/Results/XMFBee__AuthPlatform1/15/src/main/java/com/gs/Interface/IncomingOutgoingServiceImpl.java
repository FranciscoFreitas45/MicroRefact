package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.IncomingOutgoingService;
public class IncomingOutgoingServiceImpl implements IncomingOutgoingService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void addInsert(List<IncomingOutgoing> incomingOutgoings){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addInsert"))
    .queryParam("incomingOutgoings",incomingOutgoings)
;
  restTemplate.put(builder.toUriString(), null);
}


}