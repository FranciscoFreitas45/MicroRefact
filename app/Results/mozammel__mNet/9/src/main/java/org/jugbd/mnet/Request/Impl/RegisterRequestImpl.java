package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.RegisterRequest;
public class RegisterRequestImpl implements RegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setRegisters(Set<Register> registers,Long id){
 restTemplate.put("http://3/Patient/{id}/Register/setRegisters",registers,id);
 return ;
}


public Set<Register> getRegisters(Long id){
 Set<Register> aux = restTemplate.getForObject("http://3/Patient/{id}/Register/getRegisters",Set<Register>.class,id);
return aux;
}


}