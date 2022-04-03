package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentQueryOrderByListImpl implements DocumentQueryOrderByList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> toDocumentQueryOrderByList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentQueryOrderByList"))
  Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList> aux = restTemplate.getForObject(builder.toUriString(), Collector<DocumentQueryOrderBy,?,DocumentQueryOrderByList>.class);

 return aux;
}


public boolean isEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public ImmutableList<DocumentQueryOrderBy> toList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toList"))
  ImmutableList<DocumentQueryOrderBy> aux = restTemplate.getForObject(builder.toUriString(), ImmutableList<DocumentQueryOrderBy>.class);

 return aux;
}


public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofList"))
    .queryParam("list",list);
  DocumentQueryOrderByList aux = restTemplate.getForObject(builder.toUriString(), DocumentQueryOrderByList.class);

 return aux;
}


}