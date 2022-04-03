package org.vaadin.paul.spring.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.DTO.Sanitario;
import org.vaadin.paul.spring.Request.SanitarioRequest;
public class SanitarioRequestImpl implements SanitarioRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Sanitario getSanitario(int id){
 Sanitario aux = restTemplate.getForObject("http://3/Cita/{id}/Sanitario/getSanitario",Sanitario.class,id);
return aux;
}


public void setSanitario(Sanitario sanitario,int id){
 restTemplate.put("http://3/Cita/{id}/Sanitario/setSanitario",sanitario,id);
 return ;
}


}