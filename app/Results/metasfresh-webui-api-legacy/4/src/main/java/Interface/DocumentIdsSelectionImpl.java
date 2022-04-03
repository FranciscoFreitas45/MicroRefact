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


public DocumentIdsSelection fromNullable(DocumentId documentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromNullable"))
    .queryParam("documentId",documentId);
  DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(), DocumentIdsSelection.class);

 return aux;
}


public boolean isSingleDocumentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSingleDocumentId"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean isEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public String toCommaSeparatedString(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toCommaSeparatedString"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public DocumentId getSingleDocumentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSingleDocumentId"))
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public DocumentIdsSelection of(Collection<DocumentId> documentIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("documentIds",documentIds);
  DocumentIdsSelection aux = restTemplate.getForObject(builder.toUriString(), DocumentIdsSelection.class);

 return aux;
}


public ImmutableSet<T> toSet(Function<DocumentId,T> mapper){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toSet"))
    .queryParam("mapper",mapper);
  ImmutableSet<T> aux = restTemplate.getForObject(builder.toUriString(), ImmutableSet<T>.class);

 return aux;
}


}