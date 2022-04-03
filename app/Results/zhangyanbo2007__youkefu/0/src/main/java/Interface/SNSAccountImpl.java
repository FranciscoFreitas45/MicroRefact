package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SNSAccount;
public class SNSAccountImpl implements SNSAccount{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


}