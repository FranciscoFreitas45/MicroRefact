package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DebugPropertiesImpl implements DebugProperties{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public DebugProperties ofNullableMap(Map<String,?> map){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofNullableMap"))
    .queryParam("map",map);
  DebugProperties aux = restTemplate.getForObject(builder.toUriString(), DebugProperties.class);

 return aux;
}


}