package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.Brotherhood;
public class BrotherhoodImpl implements Brotherhood{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


}