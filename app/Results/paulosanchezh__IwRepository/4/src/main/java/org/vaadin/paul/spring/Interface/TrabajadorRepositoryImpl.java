package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
public class TrabajadorRepositoryImpl implements TrabajadorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Trabajador findByUser(User usuario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("usuario",usuario)
;  Trabajador aux = restTemplate.getForObject(builder.toUriString(), Trabajador.class);

 return aux;
}


}