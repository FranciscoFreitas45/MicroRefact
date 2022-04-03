package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentCollectionImpl implements DocumentCollection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public BoilerPlateContext createBoilerPlateContext(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createBoilerPlateContext"))
    .queryParam("documentPath",documentPath);
  BoilerPlateContext aux = restTemplate.getForObject(builder.toUriString(), BoilerPlateContext.class);

 return aux;
}


public DocumentPrint createDocumentPrint(DocumentPath documentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createDocumentPrint"))
    .queryParam("documentPath",documentPath);
  DocumentPrint aux = restTemplate.getForObject(builder.toUriString(), DocumentPrint.class);

 return aux;
}


}