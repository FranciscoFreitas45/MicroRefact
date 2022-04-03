package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public R forDocumentWritable(DocumentPath documentPath,IDocumentChangesCollector changesCollector,Function<Document,R> documentProcessor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forDocumentWritable"))
    .queryParam("documentPath",documentPath)
    .queryParam("changesCollector",changesCollector)
    .queryParam("documentProcessor",documentProcessor);
  R aux = restTemplate.getForObject(builder.toUriString(), R.class);

 return aux;
}


}