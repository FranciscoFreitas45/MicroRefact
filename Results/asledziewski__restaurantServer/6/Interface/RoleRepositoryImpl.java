import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RoleRepositoryImpl implements RoleRepository{

 private RestTemplate restTemplate;

  String url = "http://7";


public Optional<Role> findByRoleName(String roleName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoleName"))
    .queryParam("roleName",roleName);
  Optional<Role> aux = restTemplate.getForObject(builder.toUriString(), Optional<Role>.class)

 return aux;
}


}