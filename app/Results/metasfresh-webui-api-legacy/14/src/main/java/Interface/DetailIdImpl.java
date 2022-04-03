package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DetailIdImpl implements DetailId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public DetailId fromJson(String json){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromJson"))
    .queryParam("json",json);
  DetailId aux = restTemplate.getForObject(builder.toUriString(), DetailId.class);

 return aux;
}


}