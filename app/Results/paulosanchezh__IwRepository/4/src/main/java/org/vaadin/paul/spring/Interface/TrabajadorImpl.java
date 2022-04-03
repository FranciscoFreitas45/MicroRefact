package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.Trabajador;
public class TrabajadorImpl implements Trabajador{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String getNombre(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNombre"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public User getUser(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUser"))
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public LocalTime getHoraInicio(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHoraInicio"))
;  LocalTime aux = restTemplate.getForObject(builder.toUriString(), LocalTime.class);

 return aux;
}


public LocalTime getHoraFinal(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHoraFinal"))
;  LocalTime aux = restTemplate.getForObject(builder.toUriString(), LocalTime.class);

 return aux;
}


public Object compareTo(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/compareTo"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}