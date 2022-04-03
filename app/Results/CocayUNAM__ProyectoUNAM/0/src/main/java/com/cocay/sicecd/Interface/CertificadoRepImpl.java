package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.CertificadoRep;
public class CertificadoRepImpl implements CertificadoRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Certificado findCertificado(Integer id_profesor,Integer id_curso,Integer id_grupo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCertificado"))
    .queryParam("id_profesor",id_profesor)
    .queryParam("id_curso",id_curso)
    .queryParam("id_grupo",id_grupo)
;  Certificado aux = restTemplate.getForObject(builder.toUriString(), Certificado.class);

 return aux;
}


}