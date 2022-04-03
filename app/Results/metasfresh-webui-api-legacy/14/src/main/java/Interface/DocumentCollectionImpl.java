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


public R forRootDocumentReadonly(DocumentPath documentPath,Function<Document,R> rootDocumentProcessor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forRootDocumentReadonly"))
    .queryParam("documentPath",documentPath)
    .queryParam("rootDocumentProcessor",rootDocumentProcessor);
  R aux = restTemplate.getForObject(builder.toUriString(), R.class);

 return aux;
}


public R forDocumentReadonly(DocumentPath documentPath,Function<Document,R> documentProcessor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forDocumentReadonly"))
    .queryParam("documentPath",documentPath)
    .queryParam("documentProcessor",documentProcessor);
  R aux = restTemplate.getForObject(builder.toUriString(), R.class);

 return aux;
}


public R forRootDocumentWritable(DocumentPath documentPathOrNew,IDocumentChangesCollector changesCollector,Function<Document,R> rootDocumentProcessor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forRootDocumentWritable"))
    .queryParam("documentPathOrNew",documentPathOrNew)
    .queryParam("changesCollector",changesCollector)
    .queryParam("rootDocumentProcessor",rootDocumentProcessor);
  R aux = restTemplate.getForObject(builder.toUriString(), R.class);

 return aux;
}


}