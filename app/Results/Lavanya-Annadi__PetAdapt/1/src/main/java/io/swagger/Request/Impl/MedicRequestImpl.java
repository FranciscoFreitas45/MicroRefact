package io.swagger.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.DTO.Medic;
import io.swagger.Request.MedicRequest;
public class MedicRequestImpl implements MedicRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Medic getMedic(Long id){
 Medic aux = restTemplate.getForObject("http://3/Session/{id}/Medic/getMedic",Medic.class,id);
return aux;
}


public void setMedic(Medic medic,Long id){
 restTemplate.put("http://3/Session/{id}/Medic/setMedic",medic,id);
 return ;
}


}