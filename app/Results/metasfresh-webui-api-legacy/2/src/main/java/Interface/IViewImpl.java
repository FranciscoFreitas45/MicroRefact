package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewImpl implements IView{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public ViewId getViewId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewId"))
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


public String getTableNameOrNull(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableNameOrNull"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}