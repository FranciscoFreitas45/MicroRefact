package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String cacheReset(boolean forgetNotSavedDocuments){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cacheReset"))
    .queryParam("forgetNotSavedDocuments",forgetNotSavedDocuments);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public DocumentDescriptorFactory getDocumentDescriptorFactory(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentDescriptorFactory"))
  DocumentDescriptorFactory aux = restTemplate.getForObject(builder.toUriString(), DocumentDescriptorFactory.class);

 return aux;
}


}