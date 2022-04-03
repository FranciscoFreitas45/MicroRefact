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


}