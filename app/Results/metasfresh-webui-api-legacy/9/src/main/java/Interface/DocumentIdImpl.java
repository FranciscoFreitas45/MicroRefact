package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentIdImpl implements DocumentId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentId toIncludedRowId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toIncludedRowId"))
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public DocumentId of(RepoIdAware id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("id",id);
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public T toId(IntFunction<T> mapper){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toId"))
    .queryParam("mapper",mapper);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}