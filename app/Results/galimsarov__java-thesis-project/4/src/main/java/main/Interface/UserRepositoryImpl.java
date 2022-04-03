package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object getOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int isAdmin(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAdmin"))
    .queryParam("id",id)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}