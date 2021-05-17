import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RegistrationServiceImpl implements RegistrationService{

 private RestTemplate restTemplate;

  String url = "http://0";


public void encryptPassword(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/encryptPassword"))
    .queryParam("user",user);

  restTemplate.put(builder.toUriString(), null)



}