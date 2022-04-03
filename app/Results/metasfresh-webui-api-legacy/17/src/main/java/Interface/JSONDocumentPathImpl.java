package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JSONDocumentPathImpl implements JSONDocumentPath{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public JSONDocumentPath ofWindowDocumentPath(DocumentPath documentPath,String fieldName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofWindowDocumentPath"))
    .queryParam("documentPath",documentPath)
    .queryParam("fieldName",fieldName);
  JSONDocumentPath aux = restTemplate.getForObject(builder.toUriString(), JSONDocumentPath.class);

 return aux;
}


}