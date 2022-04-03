package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentFilterListImpl implements DocumentFilterList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public boolean isEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void forEach(Consumer<DocumentFilter> consumer){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/forEach"))
    .queryParam("consumer",consumer);

  restTemplate.put(builder.toUriString(), null);
}


public DocumentFilterList ofList(Collection<DocumentFilter> list){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofList"))
    .queryParam("list",list);
  DocumentFilterList aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterList.class);

 return aux;
}


public ImmutableList<DocumentFilter> toList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toList"))
  ImmutableList<DocumentFilter> aux = restTemplate.getForObject(builder.toUriString(), ImmutableList<DocumentFilter>.class);

 return aux;
}


}