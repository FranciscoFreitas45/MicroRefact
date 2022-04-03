package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentPathImpl implements DocumentPath{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DocumentPath includedDocumentPath(WindowId windowId,DocumentId documentId,DetailId detailId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/includedDocumentPath"))
    .queryParam("windowId",windowId)
    .queryParam("documentId",documentId)
    .queryParam("detailId",detailId);
  DocumentPath aux = restTemplate.getForObject(builder.toUriString(), DocumentPath.class);

 return aux;
}


public DocumentPath rootDocumentPath(DocumentType documentType,DocumentId documentTypeId,DocumentId documentId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/rootDocumentPath"))
    .queryParam("documentType",documentType)
    .queryParam("documentTypeId",documentTypeId)
    .queryParam("documentId",documentId);
  DocumentPath aux = restTemplate.getForObject(builder.toUriString(), DocumentPath.class);

 return aux;
}


}