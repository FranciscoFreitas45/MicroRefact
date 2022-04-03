package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.NakoupenaPolozka;
import upce.semprace.eshop.Request.NakoupenaPolozkaRequest;
public class NakoupenaPolozkaRequestImpl implements NakoupenaPolozkaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setNakoupenaPolozka(NakoupenaPolozka nakoupenaPolozka,Long id){
 restTemplate.put("http://3/Produkt/{id}/NakoupenaPolozka/setNakoupenaPolozka",nakoupenaPolozka,id);
 return ;
}


public NakoupenaPolozka getNakoupenaPolozka(Long id){
 NakoupenaPolozka aux = restTemplate.getForObject("http://3/Produkt/{id}/NakoupenaPolozka/getNakoupenaPolozka",NakoupenaPolozka.class,id);
return aux;
}


}