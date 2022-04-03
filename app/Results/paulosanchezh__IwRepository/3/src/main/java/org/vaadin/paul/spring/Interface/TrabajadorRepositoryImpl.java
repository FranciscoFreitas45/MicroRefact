package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
public class TrabajadorRepositoryImpl implements TrabajadorRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Trabajador> findByTrabajadoresNulos(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTrabajadoresNulos"))
;  List<Trabajador> aux = restTemplate.getForObject(builder.toUriString(), List<Trabajador>.class);

 return aux;
}


}