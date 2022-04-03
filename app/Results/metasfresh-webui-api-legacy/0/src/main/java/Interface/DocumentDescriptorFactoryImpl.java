package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentDescriptorFactoryImpl implements DocumentDescriptorFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public TableRecordReference getTableRecordReference(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableRecordReference"))
    .queryParam("documentPath",documentPath);
  TableRecordReference aux = restTemplate.getForObject(builder.toUriString(), TableRecordReference.class);

 return aux;
}


}