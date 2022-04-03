package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUReservationDocumentFilterServiceImpl implements HUReservationDocumentFilterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public DocumentFilter createDocumentFilterIgnoreAttributes(Packageable packageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createDocumentFilterIgnoreAttributes"))
    .queryParam("packageable",packageable);
  DocumentFilter aux = restTemplate.getForObject(builder.toUriString(), DocumentFilter.class);

 return aux;
}


}