package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JSONLookupValueImpl implements JSONLookupValue{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public int getKeyAsInt(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getKeyAsInt"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Object getCaption(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCaption"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}