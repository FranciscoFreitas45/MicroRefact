package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.DopravaRepository;
public class DopravaRepositoryImpl implements DopravaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Optional<Doprava> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Doprava> aux = restTemplate.getForObject(builder.toUriString(), Optional<Doprava>.class);

 return aux;
}


}