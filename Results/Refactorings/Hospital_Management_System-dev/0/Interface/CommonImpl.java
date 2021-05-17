import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CommonImpl implements Common{

 private RestTemplate restTemplate;

  String url = "http://5";


public ModelMap sideNavMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sideNavMap"))
  ModelMap aux = restTemplate.getForObject(builder.toUriString(), ModelMap.class)

 return aux;
}


}