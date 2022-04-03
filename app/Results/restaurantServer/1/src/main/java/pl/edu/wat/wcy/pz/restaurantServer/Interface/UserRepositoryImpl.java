package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<User> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"));
  List<User> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


public Optional<User> findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object);
  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}

public Optional<User> findByMail(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMail"))
    .queryParam("s",s);
  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}