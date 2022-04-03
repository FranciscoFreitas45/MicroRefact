package org.vaadin.paul.spring.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.Interface.CitaRepository;
public class CitaRepositoryImpl implements CitaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Cita> findByFechaAndSanitarioAndConfirmada(LocalDate fechaCita,Sanitario sanitario,boolean confirmada){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFechaAndSanitarioAndConfirmada"))
    .queryParam("fechaCita",fechaCita)
    .queryParam("sanitario",sanitario)
    .queryParam("confirmada",confirmada)
;  List<Cita> aux = restTemplate.getForObject(builder.toUriString(), List<Cita>.class);

 return aux;
}


}