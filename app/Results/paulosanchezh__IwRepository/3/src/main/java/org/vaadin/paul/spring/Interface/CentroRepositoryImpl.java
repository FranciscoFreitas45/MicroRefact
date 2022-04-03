package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.CentroRepository;
public class CentroRepositoryImpl implements CentroRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Centro findByName(String nombre,int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("nombre",nombre)
    .queryParam("id",id)
;  Centro aux = restTemplate.getForObject(builder.toUriString(), Centro.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Centro findBynombre(String nombre){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBynombre"))
    .queryParam("nombre",nombre)
;  Centro aux = restTemplate.getForObject(builder.toUriString(), Centro.class);

 return aux;
}


}