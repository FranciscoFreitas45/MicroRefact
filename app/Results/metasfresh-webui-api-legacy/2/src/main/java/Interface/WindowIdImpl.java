package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class WindowIdImpl implements WindowId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public WindowId fromNullableJson(String json){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromNullableJson"))
    .queryParam("json",json);
  WindowId aux = restTemplate.getForObject(builder.toUriString(), WindowId.class);

 return aux;
}


}