package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.PlatbaRepository;
import upce.semprace.eshop.DTO.Platba;
import java.util.*;
public class PlatbaRepositoryImpl implements PlatbaRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Optional<Platba> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Platba> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}