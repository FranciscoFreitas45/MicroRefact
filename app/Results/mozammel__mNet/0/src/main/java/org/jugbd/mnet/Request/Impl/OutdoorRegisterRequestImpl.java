package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Examination setOutdoorRegister(OutdoorRegister outdoorRegister,Long id){
 Examination aux = restTemplate.getForObject("http://3/Examination/{id}/OutdoorRegister/setOutdoorRegister?'outdoorRegister'=outdoorRegister&'id'=id',",Examination.class,id);
return aux;
}


public OutdoorRegister getOutdoorRegister(Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://3/Examination/{id}/OutdoorRegister/getOutdoorRegister",OutdoorRegister.class,id);
return aux;
}


}