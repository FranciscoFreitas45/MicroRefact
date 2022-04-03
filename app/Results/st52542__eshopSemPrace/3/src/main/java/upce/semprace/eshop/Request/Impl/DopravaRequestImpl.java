package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.Doprava;
import upce.semprace.eshop.Request.DopravaRequest;
public class DopravaRequestImpl implements DopravaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDoprava(Doprava doprava,Long id){
 restTemplate.put("http://5/Nakup/{id}/Doprava/setDoprava",doprava,id);
 return ;
}


public Doprava getDoprava(Long id){
 Doprava aux = restTemplate.getForObject("http://5/Nakup/{id}/Doprava/getDoprava",Doprava.class,id);
return aux;
}


}