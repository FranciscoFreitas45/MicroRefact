package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.Trabajador;
import org.vaadin.paul.spring.Request.TrabajadorRequest;
public class TrabajadorRequestImpl implements TrabajadorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTrabajador(Trabajador trabajador,int id){
 restTemplate.put("http://1/Sanitario/{id}/Trabajador/setTrabajador",trabajador,id);
 return ;
}


public Trabajador getTrabajador(int id){
 Trabajador aux = restTemplate.getForObject("http://1/Sanitario/{id}/Trabajador/getTrabajador",Trabajador.class,id);
return aux;
}


}