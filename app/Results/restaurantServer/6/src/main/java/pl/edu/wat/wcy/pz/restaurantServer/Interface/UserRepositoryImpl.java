package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;

public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Optional<User> findByMail(String mail){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMail"))
    .queryParam("mail",mail);
  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Boolean existsByMail(String mail){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsByMail"))
    .queryParam("mail",mail);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}