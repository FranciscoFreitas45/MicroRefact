package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentQueryOrderByListImpl implements DocumentQueryOrderByList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentQueryOrderByList parse(String orderBysListStr){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parse"))
    .queryParam("orderBysListStr",orderBysListStr);
  DocumentQueryOrderByList aux = restTemplate.getForObject(builder.toUriString(), DocumentQueryOrderByList.class);

 return aux;
}


public boolean isEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean equals(DocumentQueryOrderByList list1,DocumentQueryOrderByList list2){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("list1",list1)
    .queryParam("list2",list2);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

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