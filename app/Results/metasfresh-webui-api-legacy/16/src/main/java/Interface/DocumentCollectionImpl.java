package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentEntityDescriptor getDocumentEntityDescriptor(WindowId windowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentEntityDescriptor"))
    .queryParam("windowId",windowId);
  DocumentEntityDescriptor aux = restTemplate.getForObject(builder.toUriString(), DocumentEntityDescriptor.class);

 return aux;
}


}