package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentEntityDescriptorImpl implements DocumentEntityDescriptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


}