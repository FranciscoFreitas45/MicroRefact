package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewIdImpl implements ViewId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String getViewId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewId"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}