package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewIdImpl implements ViewId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public ViewId random(WindowId windowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/random"))
    .queryParam("windowId",windowId);
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


}