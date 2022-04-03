package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.EspecialidadRepository;
public class EspecialidadRepositoryImpl implements EspecialidadRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Especialidad> especialidadesQueNoTengaEseCentro(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/especialidadesQueNoTengaEseCentro"))
    .queryParam("id",id)
;  List<Especialidad> aux = restTemplate.getForObject(builder.toUriString(), List<Especialidad>.class);

 return aux;
}


}