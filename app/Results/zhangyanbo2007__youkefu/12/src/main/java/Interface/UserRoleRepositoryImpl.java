package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRoleRepository;
public class UserRoleRepositoryImpl implements UserRoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<UserRole> findByOrgiAndUser(String orgi,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndUser"))
    .queryParam("orgi",orgi)
    .queryParam("user",user)
;  List<UserRole> aux = restTemplate.getForObject(builder.toUriString(), List<UserRole>.class);

 return aux;
}


}