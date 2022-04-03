package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Facultad;
import co.edu.uniquindio.gri.Request.FacultadRequest;
public class FacultadRequestImpl implements FacultadRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setFacultad(Facultad facultad,long id){
 restTemplate.put("http://0/Programa/{id}/Facultad/setFacultad",facultad,id);
 return ;
}


public Facultad getFacultad(long id){
 Facultad aux = restTemplate.getForObject("http://0/Programa/{id}/Facultad/getFacultad",Facultad.class,id);
return aux;
}


}