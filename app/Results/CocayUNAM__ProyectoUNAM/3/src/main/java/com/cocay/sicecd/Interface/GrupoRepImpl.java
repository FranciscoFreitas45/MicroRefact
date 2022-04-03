package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.GrupoRep;
public class GrupoRepImpl implements GrupoRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Grupo findByClaveGrupoIdCurso(String clave,Curso curso){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByClaveGrupoIdCurso"))
    .queryParam("clave",clave)
    .queryParam("curso",curso)
;  Grupo aux = restTemplate.getForObject(builder.toUriString(), Grupo.class);

 return aux;
}


public void saveC(String clave,Integer fk_id_curso){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveC"))
    .queryParam("clave",clave)
    .queryParam("fk_id_curso",fk_id_curso)
;
  restTemplate.put(builder.toUriString(), null);
}


}