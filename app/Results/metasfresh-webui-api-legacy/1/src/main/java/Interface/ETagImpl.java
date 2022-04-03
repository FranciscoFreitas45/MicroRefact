package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ETagImpl implements ETag{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public ETag of(long version,Map<String,String> attributes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("version",version)
    .queryParam("attributes",attributes);
  ETag aux = restTemplate.getForObject(builder.toUriString(), ETag.class);

 return aux;
}


public ETag overridingAttributes(Map<String,String> overridingAttributes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/overridingAttributes"))
    .queryParam("overridingAttributes",overridingAttributes);
  ETag aux = restTemplate.getForObject(builder.toUriString(), ETag.class);

 return aux;
}


}