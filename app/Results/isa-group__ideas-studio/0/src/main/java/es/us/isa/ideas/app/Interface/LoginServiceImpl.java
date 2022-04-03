package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.LoginService;
public class LoginServiceImpl implements LoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}