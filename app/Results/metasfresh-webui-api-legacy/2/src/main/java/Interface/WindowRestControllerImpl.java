package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class WindowRestControllerImpl implements WindowRestController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentZoomIntoInfo getDocumentFieldZoomInto(Document document,String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentFieldZoomInto"))
    .queryParam("document",document)
    .queryParam("fieldName",fieldName);
  DocumentZoomIntoInfo aux = restTemplate.getForObject(builder.toUriString(), DocumentZoomIntoInfo.class);

 return aux;
}


}