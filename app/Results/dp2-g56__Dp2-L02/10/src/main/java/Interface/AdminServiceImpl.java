package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void loggedAsAdmin(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAsAdmin"))
;
  restTemplate.put(builder.toUriString(), null);
}


}