import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserRepoImpl implements UserRepo{

 private RestTemplate restTemplate;

  String url = "http://21";


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public User findByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpId"))
    .queryParam("empId",empId);
  User aux = restTemplate.getForObject(builder.toUriString(), User.class)

 return aux;
}


}