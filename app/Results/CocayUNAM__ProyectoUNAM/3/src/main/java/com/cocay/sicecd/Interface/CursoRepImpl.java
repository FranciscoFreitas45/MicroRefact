package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.CursoRep;
public class CursoRepImpl implements CursoRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Curso findByUniqueClaveCurso(String clave){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUniqueClaveCurso"))
    .queryParam("clave",clave)
;  Curso aux = restTemplate.getForObject(builder.toUriString(), Curso.class);

 return aux;
}


public void saveC(String clave,String nombre){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveC"))
    .queryParam("clave",clave)
    .queryParam("nombre",nombre)
;
  restTemplate.put(builder.toUriString(), null);
}


}