package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.Cita;
import org.vaadin.paul.spring.Request.CitaRequest;
public class CitaRequestImpl implements CitaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Cita> getCitas(int id){
 List<Cita> aux = restTemplate.getForObject("http://0/HistorialClinico/{id}/Cita/getCitas",List<Cita>.class,id);
return aux;
}


}