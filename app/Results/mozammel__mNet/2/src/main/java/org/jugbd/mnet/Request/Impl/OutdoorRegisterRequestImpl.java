package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Visit setOutdoorRegister(OutdoorRegister outdoorRegister,Long id){
 Visit aux = restTemplate.getForObject("http://3/Visit/{id}/OutdoorRegister/setOutdoorRegister?'outdoorRegister'=outdoorRegister&'id'=id',",Visit.class,id);
return aux;
}


public OutdoorRegister getOutdoorRegister(Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://3/Visit/{id}/OutdoorRegister/getOutdoorRegister",OutdoorRegister.class,id);
return aux;
}


}