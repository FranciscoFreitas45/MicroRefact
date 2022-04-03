package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CacheServiceI;
public class CacheServiceIImpl implements CacheServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Object get(String cacheName,Object key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("cacheName",cacheName)
    .queryParam("key",key)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}