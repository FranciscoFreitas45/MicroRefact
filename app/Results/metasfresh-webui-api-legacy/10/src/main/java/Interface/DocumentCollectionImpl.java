package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public TableRecordReference getTableRecordReference(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableRecordReference"))
    .queryParam("documentPath",documentPath);
  TableRecordReference aux = restTemplate.getForObject(builder.toUriString(), TableRecordReference.class);

 return aux;
}


public Object getModel(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getModel"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void invalidateDocumentByRecordId(String tableName,int recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateDocumentByRecordId"))
    .queryParam("tableName",tableName)
    .queryParam("recordId",recordId);

  restTemplate.put(builder.toUriString(), null);
}


}