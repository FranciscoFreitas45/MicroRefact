package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.InscripcionRep;
public class InscripcionRepImpl implements InscripcionRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void saveI(int fk_id_grupo,int fk_id_profesor,String calif,boolean aprobado){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveI"))
    .queryParam("fk_id_grupo",fk_id_grupo)
    .queryParam("fk_id_profesor",fk_id_profesor)
    .queryParam("calif",calif)
    .queryParam("aprobado",aprobado)
;
  restTemplate.put(builder.toUriString(), null);
}


}