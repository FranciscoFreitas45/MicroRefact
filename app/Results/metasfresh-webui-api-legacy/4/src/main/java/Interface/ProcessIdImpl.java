package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProcessIdImpl implements ProcessId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public DocumentId toDocumentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDocumentId"))
  DocumentId aux = restTemplate.getForObject(builder.toUriString(), DocumentId.class);

 return aux;
}


}