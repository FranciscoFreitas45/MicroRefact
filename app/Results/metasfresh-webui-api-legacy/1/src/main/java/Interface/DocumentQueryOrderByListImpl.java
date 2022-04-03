package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentQueryOrderByListImpl implements DocumentQueryOrderByList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentQueryOrderByList ofList(List<DocumentQueryOrderBy> list){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofList"))
    .queryParam("list",list);
  DocumentQueryOrderByList aux = restTemplate.getForObject(builder.toUriString(), DocumentQueryOrderByList.class);

 return aux;
}


public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("list1",list1)
    .queryParam("list2",list2);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public ImmutableList<DocumentQueryOrderBy> toList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toList"))
  ImmutableList<DocumentQueryOrderBy> aux = restTemplate.getForObject(builder.toUriString(), ImmutableList<DocumentQueryOrderBy>.class);

 return aux;
}


}