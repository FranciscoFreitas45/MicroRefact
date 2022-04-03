package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ChiefComplaint setOutdoorRegister(OutdoorRegister outdoorRegister,Long id){
 ChiefComplaint aux = restTemplate.getForObject("http://3/ChiefComplaint/{id}/OutdoorRegister/setOutdoorRegister?'outdoorRegister'=outdoorRegister&'id'=id',",ChiefComplaint.class,id);
return aux;
}


public OutdoorRegister getOutdoorRegister(Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://3/ChiefComplaint/{id}/OutdoorRegister/getOutdoorRegister",OutdoorRegister.class,id);
return aux;
}


}