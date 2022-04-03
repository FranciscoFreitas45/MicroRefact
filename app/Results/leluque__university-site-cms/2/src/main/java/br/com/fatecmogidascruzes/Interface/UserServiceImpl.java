package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Optional<User> getByUsername(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByUsername"))
    .queryParam("name",name)
;  Optional<User> aux = restTemplate.getForObject(builder.toUriString(), Optional<User>.class);

 return aux;
}


}