package upce.semprace.eshop.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.DTO.Produkt;
import upce.semprace.eshop.Request.ProduktRequest;
public class ProduktRequestImpl implements ProduktRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setProdukt(Produkt produkt,Long id){
 restTemplate.put("http://4/NakoupenaPolozka/{id}/Produkt/setProdukt",produkt,id);
 return ;
}


public Produkt getProdukt(Long id){
 Produkt aux = restTemplate.getForObject("http://4/NakoupenaPolozka/{id}/Produkt/getProdukt",Produkt.class,id);
return aux;
}


}