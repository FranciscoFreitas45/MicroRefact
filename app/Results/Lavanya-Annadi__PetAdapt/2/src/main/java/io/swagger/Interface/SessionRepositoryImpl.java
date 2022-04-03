package io.swagger.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.Interface.SessionRepository;
public class SessionRepositoryImpl implements SessionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Session findBySessionId(String session){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySessionId"))
    .queryParam("session",session)
;  Session aux = restTemplate.getForObject(builder.toUriString(), Session.class);

 return aux;
}


}