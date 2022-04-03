package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class QuickInputDescriptorFactoryServiceImpl implements QuickInputDescriptorFactoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://14";


public boolean hasQuickInputEntityDescriptor(QuickInputDescriptorKey key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasQuickInputEntityDescriptor"))
    .queryParam("key",key);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}