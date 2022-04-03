package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.Platba;
import upce.semprace.eshop.Request.PlatbaRequest;
public class PlatbaRequestImpl implements PlatbaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Platba getPlatba(Long id){
 Platba aux = restTemplate.getForObject("http://6/Nakup/{id}/Platba/getPlatba",Platba.class,id);
return aux;
}


public void setPlatba(Platba platba,Long id){
 restTemplate.put("http://6/Nakup/{id}/Platba/setPlatba",platba,id);
 return ;
}


}