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


public Comparator<T> toComparator(FieldValueExtractor<T> fieldValueExtractor,JSONOptions jsonOpts){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toComparator"))
    .queryParam("fieldValueExtractor",fieldValueExtractor)
    .queryParam("jsonOpts",jsonOpts);
  Comparator<T> aux = restTemplate.getForObject(builder.toUriString(), Comparator<T>.class);

 return aux;
}


}