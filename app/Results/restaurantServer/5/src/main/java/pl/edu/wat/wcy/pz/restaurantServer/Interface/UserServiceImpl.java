package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserService;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;
import java.util.*;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Optional<User> getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id);
  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional.class);

 return aux;
}


}