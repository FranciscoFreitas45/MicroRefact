package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TSInterfaceServiceI;
public class TSInterfaceServiceIImpl implements TSInterfaceServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}