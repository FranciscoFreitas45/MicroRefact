package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewIdImpl implements ViewId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public ViewId ofViewIdString(String viewIdStr,WindowId expectedWindowId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofViewIdString"))
    .queryParam("viewIdStr",viewIdStr)
    .queryParam("expectedWindowId",expectedWindowId);
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


}