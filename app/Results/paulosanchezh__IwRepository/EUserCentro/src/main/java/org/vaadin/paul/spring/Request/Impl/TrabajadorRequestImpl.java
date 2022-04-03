package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.Trabajador;
import org.vaadin.paul.spring.Request.TrabajadorRequest;
public class TrabajadorRequestImpl implements TrabajadorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Trabajador> getTrabajadores(int id){
 List<Trabajador> aux = restTemplate.getForObject("http://1/Centro/{id}/Trabajador/getTrabajadores",List<Trabajador>.class,id);
return aux;
}


public void setTrabajadores(List<Trabajador> trabajadores,int id){
 restTemplate.put("http://1/Centro/{id}/Trabajador/setTrabajadores",trabajadores,id);
 return ;
}


}