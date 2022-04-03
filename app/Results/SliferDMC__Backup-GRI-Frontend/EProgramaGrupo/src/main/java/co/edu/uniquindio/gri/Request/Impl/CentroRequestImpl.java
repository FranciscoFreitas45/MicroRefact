package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Centro;
import co.edu.uniquindio.gri.Request.CentroRequest;
public class CentroRequestImpl implements CentroRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Centro getCentro(long id){
 Centro aux = restTemplate.getForObject("http://0/Grupo/{id}/Centro/getCentro",Centro.class,id);
return aux;
}


public void setCentro(Centro centro,long id){
 restTemplate.put("http://0/Grupo/{id}/Centro/setCentro",centro,id);
 return ;
}


}