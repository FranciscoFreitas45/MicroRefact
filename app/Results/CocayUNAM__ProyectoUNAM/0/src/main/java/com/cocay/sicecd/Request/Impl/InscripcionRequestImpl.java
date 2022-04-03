package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Inscripcion;
import com.cocay.sicecd.Request.InscripcionRequest;
public class InscripcionRequestImpl implements InscripcionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Inscripcion> getInscripciones(int pk_id_profesor){
 List<Inscripcion> aux = restTemplate.getForObject("http://1/Profesor/{id}/Inscripcion/getInscripciones",List<Inscripcion>.class,pk_id_profesor);
return aux;
}


public void setInscripciones(List<Inscripcion> inscripciones,int pk_id_profesor){
 restTemplate.put("http://1/Profesor/{id}/Inscripcion/setInscripciones",inscripciones,pk_id_profesor);
 return ;
}


}