package pl.edu.wat.wcy.pz.restaurantServer.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RoleRepository;
public class RoleRepositoryImpl implements RoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Optional<Role> findByRoleName(String roleName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoleName"))
    .queryParam("roleName",roleName)
;  Optional<Role> aux = restTemplate.getForObject(builder.toUriString(), Optional<Role>.class);

 return aux;
}


}