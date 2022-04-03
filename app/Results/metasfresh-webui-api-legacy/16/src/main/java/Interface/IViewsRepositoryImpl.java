package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewsRepositoryImpl implements IViewsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public IView createView(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createView"))
    .queryParam("request",request);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public T getView(ViewId viewId,Class<T> type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getView"))
    .queryParam("viewId",viewId)
    .queryParam("type",type);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewLayout"))
    .queryParam("windowId",windowId)
    .queryParam("viewDataType",viewDataType)
    .queryParam("profileId",profileId);
  ViewLayout aux = restTemplate.getForObject(builder.toUriString(), ViewLayout.class);

 return aux;
}


}