package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentReferencesServiceImpl implements DocumentReferencesService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentReference getDocumentReference(DocumentPath sourceDocumentPath,WindowId targetWindowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDocumentReference"))
    .queryParam("sourceDocumentPath",sourceDocumentPath)
    .queryParam("targetWindowId",targetWindowId);
  DocumentReference aux = restTemplate.getForObject(builder.toUriString(), DocumentReference.class);

 return aux;
}


}