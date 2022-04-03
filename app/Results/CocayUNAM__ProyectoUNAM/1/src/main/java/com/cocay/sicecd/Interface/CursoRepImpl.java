package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.CursoRep;
public class CursoRepImpl implements CursoRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Curso> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Curso> aux = restTemplate.getForObject(builder.toUriString(), List<Curso>.class);

 return aux;
}


public Curso findForClave(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForClave"))
    .queryParam("clave",clave)
;  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class);

 return aux;
}


}