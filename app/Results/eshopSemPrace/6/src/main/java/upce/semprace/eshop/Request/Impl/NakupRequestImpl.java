package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.Nakup;
import upce.semprace.eshop.Request.NakupRequest;
import java.util.*;
public class NakupRequestImpl implements NakupRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Nakup> getNakup(Long id){
 Set<Nakup> aux = restTemplate.getForObject("http://3/Platba/{id}/Nakup/getNakup",Set.class,id);
return aux;
}


public void setNakup(Set<Nakup> nakup,Long id){
 restTemplate.put("http://3/Platba/{id}/Nakup/setNakup",nakup,id);
 return ;
}


}