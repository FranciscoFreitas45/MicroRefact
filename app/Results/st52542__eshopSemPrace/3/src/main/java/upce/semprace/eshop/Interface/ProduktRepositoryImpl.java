package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.ProduktRepository;
public class ProduktRepositoryImpl implements ProduktRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<Produkt> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Produkt> aux = restTemplate.getForObject(builder.toUriString(), Optional<Produkt>.class);

 return aux;
}


}