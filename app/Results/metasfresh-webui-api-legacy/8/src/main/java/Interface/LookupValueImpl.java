package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LookupValueImpl implements LookupValue{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public String getDisplayName(String adLanguage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisplayName"))
    .queryParam("adLanguage",adLanguage);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getIdAsString(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdAsString"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getIdAsInt(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdAsInt"))
  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public T getIdAs(IntFunction<T> idMapper){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIdAs"))
    .queryParam("idMapper",idMapper);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}