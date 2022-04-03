package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.EntityService;
public class EntityServiceImpl implements EntityService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public T getRowEntity(T domain){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRowEntity"))
    .queryParam("domain",domain)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}