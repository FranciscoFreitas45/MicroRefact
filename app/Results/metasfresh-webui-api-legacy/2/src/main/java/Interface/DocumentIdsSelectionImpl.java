package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentIdsSelectionImpl implements DocumentIdsSelection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public DocumentIdsSelection ofCommaSeparatedString(String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofCommaSeparatedString"))
    .queryParam("string",string);
  DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(), DocumentIdsSelection.class);

 return aux;
}


public DocumentIdsSelection ofStringSet(Collection<String> stringDocumentIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofStringSet"))
    .queryParam("stringDocumentIds",stringDocumentIds);
  DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(), DocumentIdsSelection.class);

 return aux;
}


public boolean isEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}