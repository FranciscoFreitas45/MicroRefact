package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LookupValueImpl implements LookupValue{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public ITranslatableString getDisplayNameTrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisplayNameTrl"))
  ITranslatableString aux = restTemplate.getForObject(builder.toUriString(), ITranslatableString.class);

 return aux;
}


public int getIdAsInt(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdAsInt"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}