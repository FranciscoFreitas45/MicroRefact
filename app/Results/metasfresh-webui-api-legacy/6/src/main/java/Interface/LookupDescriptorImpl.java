package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LookupDescriptorImpl implements LookupDescriptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public boolean isNumericKey(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isNumericKey"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public T castOrNull(Class<T> lookupDescriptorClass){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/castOrNull"))
    .queryParam("lookupDescriptorClass",lookupDescriptorClass);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}