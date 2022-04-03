package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewIdImpl implements ViewId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public ViewId withWindowId(WindowId newWindowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/withWindowId"))
    .queryParam("newWindowId",newWindowId);
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


}