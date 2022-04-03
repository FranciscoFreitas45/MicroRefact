package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class FacetFilterViewCacheMapImpl implements FacetFilterViewCacheMap{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public FacetFilterViewCacheMap newInstance(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newInstance"))
  FacetFilterViewCacheMap aux = restTemplate.getForObject(builder.toUriString(), FacetFilterViewCacheMap.class);

 return aux;
}


}