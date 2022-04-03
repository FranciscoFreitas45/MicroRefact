package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class BuilderImpl implements Builder{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<String> getLookupTableName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLookupTableName"))
  Optional<String> aux = restTemplate.getForObject(builder.toUriString(), Optional<String>.class);

 return aux;
}


public boolean isSpecialFieldToExcludeFromLayout(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSpecialFieldToExcludeFromLayout"))
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}