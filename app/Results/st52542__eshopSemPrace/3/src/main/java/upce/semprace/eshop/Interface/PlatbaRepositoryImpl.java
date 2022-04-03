package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.PlatbaRepository;
public class PlatbaRepositoryImpl implements PlatbaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Optional<Platba> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Platba> aux = restTemplate.getForObject(builder.toUriString(), Optional<Platba>.class);

 return aux;
}


}