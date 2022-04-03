package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentFilterListImpl implements DocumentFilterList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public DocumentFilterList mergeWith(DocumentFilter filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/mergeWith"))
    .queryParam("filter",filter);
  DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterList.class);

 return aux;
}


public Collector<DocumentFilter,?,DocumentFilterList> toDocumentFilterList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentFilterList"))
  Collector<DocumentFilter,?,DocumentFilterList> aux = restTemplate.getForObject(builder.toUriString(), Collector<DocumentFilter,?,DocumentFilterList>.class);

 return aux;
}


public Stream<DocumentFilter> stream(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
  Stream<DocumentFilter> aux = restTemplate.getForObject(builder.toUriString(), Stream<DocumentFilter>.class);

 return aux;
}


public Object filter(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filter"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}