package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class PurchaseViewLayoutFactoryImpl implements PurchaseViewLayoutFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object builder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object caption(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/caption"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewLayout"))
    .queryParam("windowId",windowId)
    .queryParam("viewDataType",viewDataType);
  ViewLayout aux = restTemplate.getForObject(builder.toUriString(), ViewLayout.class);

 return aux;
}


}