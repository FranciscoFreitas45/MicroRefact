package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CacheServiceI;
public class CacheServiceIImpl implements CacheServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public void clean(String cacheName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clean"))
    .queryParam("cacheName",cacheName)
;
  restTemplate.put(builder.toUriString(), null);
}


}