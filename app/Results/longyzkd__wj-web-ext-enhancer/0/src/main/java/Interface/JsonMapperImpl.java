package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JsonMapper;
public class JsonMapperImpl implements JsonMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String toJsonString(Object object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toJsonString"))
    .queryParam("object",object)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}