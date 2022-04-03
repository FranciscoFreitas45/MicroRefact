package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.Organ;
public class OrganImpl implements Organ{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}