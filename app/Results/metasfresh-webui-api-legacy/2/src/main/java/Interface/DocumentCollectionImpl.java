package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean isValidDocumentPath(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isValidDocumentPath"))
    .queryParam("documentPath",documentPath);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public R forDocumentReadonly(DocumentPath documentPath,Function<Document,R> documentProcessor){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forDocumentReadonly"))
    .queryParam("documentPath",documentPath)
    .queryParam("documentProcessor",documentProcessor);
  R aux = restTemplate.getForObject(builder.toUriString(), R.class);

 return aux;
}


}