package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentIdImpl implements DocumentId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String toJson(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toJson"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public DocumentId ofString(String idStr){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofString"))
    .queryParam("idStr",idStr);
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


public DocumentId of(RepoIdAware id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("id",id);
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


}