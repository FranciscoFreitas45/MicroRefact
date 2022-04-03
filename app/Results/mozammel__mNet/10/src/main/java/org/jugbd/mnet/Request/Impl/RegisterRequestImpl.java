package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.RegisterRequest;
public class RegisterRequestImpl implements RegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public ChiefComplaint setRegister(Register register,Long id){
 ChiefComplaint aux = restTemplate.getForObject("http://3/ChiefComplaint/{id}/Register/setRegister?'register'=register&'id'=id',",ChiefComplaint.class,id);
return aux;
}


public Register getRegister(Long id){
 Register aux = restTemplate.getForObject("http://3/ChiefComplaint/{id}/Register/getRegister",Register.class,id);
return aux;
}


}