package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProductLookupDescriptorImpl implements ProductLookupDescriptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object builderWithoutStockInfo(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builderWithoutStockInfo"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object bpartnerParamName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/bpartnerParamName"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}