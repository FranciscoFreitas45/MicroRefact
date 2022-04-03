package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class JSONOptionsImpl implements JSONOptions{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object builder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object adLanguage(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/adLanguage"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public JSONOptions newInstance(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))
  JSONOptions aux = restTemplate.getForObject(builder.toUriString(), JSONOptions.class);

 return aux;
}


}