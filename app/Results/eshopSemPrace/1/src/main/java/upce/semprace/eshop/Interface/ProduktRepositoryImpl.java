package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.ProduktRepository;
import upce.semprace.eshop.DTO.Produkt;
import java.util.*;
public class ProduktRepositoryImpl implements ProduktRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://localhost:8084";


public Optional<Produkt> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Produkt> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}
public Produkt save(Produkt produkt){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("produkt",produkt)
;  Produkt aux = restTemplate.getForObject(builder.toUriString(), Produkt.class);

 return aux;
}



}