package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.RegisterRequest;
public class RegisterRequestImpl implements RegisterRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Examination setRegister(Register register,Long id){
 Examination aux = restTemplate.getForObject("http://3/Examination/{id}/Register/setRegister?'register'=register&'id'=id',",Examination.class,id);
return aux;
}


public Register getRegister(Long id){
 Register aux = restTemplate.getForObject("http://3/Examination/{id}/Register/getRegister",Register.class,id);
return aux;
}


}