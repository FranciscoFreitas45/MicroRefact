package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.Centro;
import org.vaadin.paul.spring.Request.CentroRequest;
public class CentroRequestImpl implements CentroRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Centro getCentro(int id){
 Centro aux = restTemplate.getForObject("http://4/Trabajador/{id}/Centro/getCentro",Centro.class,id);
return aux;
}


public void setCentro(Centro centro,int id){
 restTemplate.put("http://4/Trabajador/{id}/Centro/setCentro",centro,id);
 return ;
}


}