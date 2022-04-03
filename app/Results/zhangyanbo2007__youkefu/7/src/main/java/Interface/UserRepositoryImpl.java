package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<User> findByIdAndOrgi(String id,String orgi,Pageable paramPageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
    .queryParam("paramPageable",paramPageable)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public List<User> findAll(Specification<User> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec);
  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}