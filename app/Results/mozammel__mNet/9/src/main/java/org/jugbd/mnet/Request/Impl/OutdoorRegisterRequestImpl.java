package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.OutdoorRegister;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
public class OutdoorRegisterRequestImpl implements OutdoorRegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<OutdoorRegister> getOutdoorRegisters(Long id){
 Set<OutdoorRegister> aux = restTemplate.getForObject("http://3/Patient/{id}/OutdoorRegister/getOutdoorRegisters",Set<OutdoorRegister>.class,id);
return aux;
}


public Patient setOutdoorRegisters(Set<OutdoorRegister> outdoorRegisters,Long id){
 Patient aux = restTemplate.getForObject("http://3/Patient/{id}/OutdoorRegister/setOutdoorRegisters?'outdoorRegisters'=outdoorRegisters&'id'=id',",Patient.class,id);
return aux;
}


}