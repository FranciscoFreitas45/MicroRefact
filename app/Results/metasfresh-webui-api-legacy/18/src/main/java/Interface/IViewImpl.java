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


public Object getClass(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClass"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public ViewId getParentViewId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParentViewId"))
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


}