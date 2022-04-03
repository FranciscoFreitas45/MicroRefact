package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ASIDocumentImpl implements ASIDocument{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentId getDocumentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentId"))
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public Collection<IDocumentFieldView> getFieldViews(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFieldViews"))
  Collection<IDocumentFieldView> aux = restTemplate.getForObject(builder.toUriString(), Collection<IDocumentFieldView>.class);

 return aux;
}


public Object stream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object map(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/map"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}