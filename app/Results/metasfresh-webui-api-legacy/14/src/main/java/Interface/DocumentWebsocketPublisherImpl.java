package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentWebsocketPublisherImpl implements DocumentWebsocketPublisher{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void convertAndPublish(List<JSONDocument> jsonDocumentEvents){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertAndPublish"))
    .queryParam("jsonDocumentEvents",jsonDocumentEvents);

  restTemplate.put(builder.toUriString(), null);
}


}