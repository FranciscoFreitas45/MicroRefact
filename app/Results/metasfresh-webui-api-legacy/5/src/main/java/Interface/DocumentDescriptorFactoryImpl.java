package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentDescriptorFactoryImpl implements DocumentDescriptorFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void invalidateForWindow(WindowId windowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateForWindow"))
    .queryParam("windowId",windowId);

  restTemplate.put(builder.toUriString(), null);
}


}