package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewIdImpl implements ViewId{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String getViewIdPart(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewIdPart"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getPartAsInt(int index){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPartAsInt"))
    .queryParam("index",index);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public ViewId ofParts(WindowId windowId,String viewIdPart,String otherParts){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofParts"))
    .queryParam("windowId",windowId)
    .queryParam("viewIdPart",viewIdPart)
    .queryParam("otherParts",otherParts);
  ViewId aux = restTemplate.getForObject(builder.toUriString(), ViewId.class);

 return aux;
}


}