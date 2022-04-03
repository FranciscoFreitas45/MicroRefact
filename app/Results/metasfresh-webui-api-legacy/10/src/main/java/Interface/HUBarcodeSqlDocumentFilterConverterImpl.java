package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUBarcodeSqlDocumentFilterConverterImpl implements HUBarcodeSqlDocumentFilterConverter{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public DocumentFilterDescriptor createDocumentFilterDescriptor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createDocumentFilterDescriptor"))
  DocumentFilterDescriptor aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptor.class);

 return aux;
}


}