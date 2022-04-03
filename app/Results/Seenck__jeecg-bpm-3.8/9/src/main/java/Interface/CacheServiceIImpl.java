package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CacheServiceI;
public class CacheServiceIImpl implements CacheServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public void put(String cacheName,Object key,Object value){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/put"))
    .queryParam("cacheName",cacheName)
    .queryParam("key",key)
    .queryParam("value",value)
;
  restTemplate.put(builder.toUriString(), null);
}


}