package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.Request.UzivatelRequest;
public class UzivatelRequestImpl implements UzivatelRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setUzivatel(Uzivatel uzivatel,Long id){
 restTemplate.put("http://7/Nakup/{id}/Uzivatel/setUzivatel",uzivatel,id);
 return ;
}


public Uzivatel getUzivatel(Long id){
 Uzivatel aux = restTemplate.getForObject("http://7/Nakup/{id}/Uzivatel/getUzivatel",Uzivatel.class,id);
return aux;
}


}