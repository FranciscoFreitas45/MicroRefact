package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void invalidateDocumentByRecordId(String tableName,int recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateDocumentByRecordId"))
    .queryParam("tableName",tableName)
    .queryParam("recordId",recordId);

  restTemplate.put(builder.toUriString(), null);
}


public String cacheReset(boolean forgetNotSavedDocuments){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cacheReset"))
    .queryParam("forgetNotSavedDocuments",forgetNotSavedDocuments);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}