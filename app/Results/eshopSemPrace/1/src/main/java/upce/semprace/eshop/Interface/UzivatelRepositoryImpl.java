package upce.semprace.eshop.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.DTO.Uzivatel;
import java.util.*;
public class UzivatelRepositoryImpl implements UzivatelRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Optional<Uzivatel> findByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("username",username)
;  Optional<Uzivatel> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getId(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Optional<Uzivatel> findById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Optional<Uzivatel> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}