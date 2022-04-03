package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RTableRepository;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.RTable;
public class RTableRepositoryImpl implements RTableRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Optional<RTable> findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object);
  Optional<RTable> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}