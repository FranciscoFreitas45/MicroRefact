import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UserRepoImpl implements UserRepo{

 private RestTemplate restTemplate;

  String url = "http://21";


public int updateIsVistStatus(int empId,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateIsVistStatus"))
    .queryParam("empId",empId)
    .queryParam("password",password);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}