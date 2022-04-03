package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Profesor;
import com.cocay.sicecd.Request.ProfesorRequest;
public class ProfesorRequestImpl implements ProfesorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Profesor getFk_id_profesor(int pk_id_profesor){
 Profesor aux = restTemplate.getForObject("http://0/Certificado/{id}/Profesor/getFk_id_profesor",Profesor.class,pk_id_profesor);
return aux;
}


public void setFk_id_profesor(Profesor fk_id_profesor,int pk_id_profesor){
 restTemplate.put("http://0/Certificado/{id}/Profesor/setFk_id_profesor",fk_id_profesor,pk_id_profesor);
 return ;
}


}