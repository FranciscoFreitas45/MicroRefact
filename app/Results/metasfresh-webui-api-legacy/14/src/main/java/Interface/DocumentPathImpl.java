package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentPathImpl implements DocumentPath{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/rootDocumentPath"))
    .queryParam("documentType",documentType)
    .queryParam("documentTypeId",documentTypeId)
    .queryParam("documentId",documentId);
  DocumentPath aux = restTemplate.getForObject(builder.toUriString(), DocumentPath.class);

 return aux;
}


}