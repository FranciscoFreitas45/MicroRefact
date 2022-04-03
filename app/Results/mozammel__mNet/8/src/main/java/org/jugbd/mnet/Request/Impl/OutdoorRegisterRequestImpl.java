package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Vital setOutdoorRegister(OutdoorRegister outdoorRegister,Long id){
 Vital aux = restTemplate.getForObject("http://3/Vital/{id}/OutdoorRegister/setOutdoorRegister?'outdoorRegister'=outdoorRegister&'id'=id',",Vital.class,id);
return aux;
}


public OutdoorRegister getOutdoorRegister(Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://3/Vital/{id}/OutdoorRegister/getOutdoorRegister",OutdoorRegister.class,id);
return aux;
}


}