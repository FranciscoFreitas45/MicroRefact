package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.NakupRepository;
import java.util.*;
import upce.semprace.eshop.DTO.*;
public class NakupRepositoryImpl implements NakupRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://localhost:8083";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("NakuRepository/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}

public List<Nakup> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("NakuRepository/findAll"));
  List<Nakup> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}



}