package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.ProfesorRep;
public class ProfesorRepImpl implements ProfesorRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Profesor> findByCompleteNameList(String nombre,String apellido_paterno,String apellido_materno){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCompleteNameList"))
    .queryParam("nombre",nombre)
    .queryParam("apellido_paterno",apellido_paterno)
    .queryParam("apellido_materno",apellido_materno)
;  List<Profesor> aux = restTemplate.getForObject(builder.toUriString(), List<Profesor>.class);

 return aux;
}


}