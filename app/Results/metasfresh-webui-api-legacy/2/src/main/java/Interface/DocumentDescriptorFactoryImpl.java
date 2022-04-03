package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentDescriptorFactoryImpl implements DocumentDescriptorFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentEntityDescriptor getDocumentEntityDescriptor(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentEntityDescriptor"))
    .queryParam("documentPath",documentPath);
  DocumentEntityDescriptor aux = restTemplate.getForObject(builder.toUriString(), DocumentEntityDescriptor.class);

 return aux;
}


}