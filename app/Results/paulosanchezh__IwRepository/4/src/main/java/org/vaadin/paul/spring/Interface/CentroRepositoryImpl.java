package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.CentroRepository;
public class CentroRepositoryImpl implements CentroRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Centro> findByLocalidad(Localidad Localidad){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLocalidad"))
    .queryParam("Localidad",Localidad)
;  List<Centro> aux = restTemplate.getForObject(builder.toUriString(), List<Centro>.class);

 return aux;
}


}