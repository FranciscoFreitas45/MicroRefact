package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.User;
import org.vaadin.paul.spring.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getPaciente(int id){
 User aux = restTemplate.getForObject("http://2/Cita/{id}/User/getPaciente",User.class,id);
return aux;
}


public void setPaciente(User user,int id){
 restTemplate.put("http://2/Cita/{id}/User/setPaciente",user,id);
 return ;
}


}