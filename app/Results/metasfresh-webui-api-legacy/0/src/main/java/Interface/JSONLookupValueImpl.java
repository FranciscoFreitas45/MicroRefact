package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JSONLookupValueImpl implements JSONLookupValue{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public JSONLookupValue of(String key,String caption,String description){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("key",key)
    .queryParam("caption",caption)
    .queryParam("description",description);
  JSONLookupValue aux = restTemplate.getForObject(builder.toUriString(), JSONLookupValue.class);

 return aux;
}


}