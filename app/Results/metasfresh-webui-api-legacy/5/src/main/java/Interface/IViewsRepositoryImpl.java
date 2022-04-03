package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewsRepositoryImpl implements IViewsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public IView getViewIfExists(ViewId viewId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewIfExists"))
    .queryParam("viewId",viewId);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public IView createView(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createView"))
    .queryParam("request",request);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public void closeView(ViewId viewId,ViewCloseAction closeAction){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/closeView"))
    .queryParam("viewId",viewId)
    .queryParam("closeAction",closeAction);

  restTemplate.put(builder.toUriString(), null);
}


}