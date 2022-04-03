package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentFilterDescriptorsProviderImpl implements DocumentFilterDescriptorsProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public DocumentFilterDescriptor getByFilterId(String filterId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByFilterId"))
    .queryParam("filterId",filterId);
  DocumentFilterDescriptor aux = restTemplate.getForObject(builder.toUriString(), DocumentFilterDescriptor.class);

 return aux;
}


}