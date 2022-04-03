package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
public class SanitarioRepositoryImpl implements SanitarioRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Sanitario findByTrabajador(Trabajador trabajador){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTrabajador"))
    .queryParam("trabajador",trabajador)
;  Sanitario aux = restTemplate.getForObject(builder.toUriString(), Sanitario.class);

 return aux;
}


}