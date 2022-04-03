package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.ProfesorRep;
public class ProfesorRepImpl implements ProfesorRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Profesor findByCorreo(String correo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCorreo"))
    .queryParam("correo",correo)
;  Profesor aux = restTemplate.getForObject(builder.toUriString(), Profesor.class);

 return aux;
}


public List<Profesor> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Profesor> aux = restTemplate.getForObject(builder.toUriString(), List<Profesor>.class);

 return aux;
}


}