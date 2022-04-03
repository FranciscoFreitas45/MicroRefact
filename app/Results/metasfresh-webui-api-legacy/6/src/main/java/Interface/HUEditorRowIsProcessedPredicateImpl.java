package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUEditorRowIsProcessedPredicateImpl implements HUEditorRowIsProcessedPredicate{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public boolean isProcessed(I_M_HU hu){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isProcessed"))
    .queryParam("hu",hu);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}