package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ETagImpl implements ETag{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}