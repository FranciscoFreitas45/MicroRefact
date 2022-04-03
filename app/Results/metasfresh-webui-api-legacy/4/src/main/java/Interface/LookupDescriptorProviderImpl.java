package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LookupDescriptorProviderImpl implements LookupDescriptorProvider{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Optional<LookupDescriptor> provide(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/provide"))
  Optional<LookupDescriptor> aux = restTemplate.getForObject(builder.toUriString(), Optional<LookupDescriptor>.class);

 return aux;
}


public Optional<LookupDescriptor> provideForFilter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/provideForFilter"))
  Optional<LookupDescriptor> aux = restTemplate.getForObject(builder.toUriString(), Optional<LookupDescriptor>.class);

 return aux;
}


public Optional<String> getTableName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableName"))
  Optional<String> aux = restTemplate.getForObject(builder.toUriString(), Optional<String>.class);

 return aux;
}


public boolean isNumericKey(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isNumericKey"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Object orElse(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/orElse"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}