import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class StateManagerImpl implements StateManager{

 private RestTemplate restTemplate;

  String url = "http://9";


public List<Error> getError(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getError"))
  List<Error> aux = restTemplate.getForObject(builder.toUriString(), List<Error>.class)

 return aux;
}


public Object isEmpty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public void setError(List<Error> errModels){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setError"))
    .queryParam("errModels",errModels);

  restTemplate.put(builder.toUriString(), null)



public Object getValue(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getValue"))
    .queryParam("key",key);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}