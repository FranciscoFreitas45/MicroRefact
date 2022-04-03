package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewsRepositoryImpl implements IViewsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public T getView(ViewId viewId,Class<T> type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getView"))
    .queryParam("viewId",viewId)
    .queryParam("type",type);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public IView createView(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createView"))
    .queryParam("request",request);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public IView filterView(ViewId viewId,JSONFilterViewRequest jsonRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/filterView"))
    .queryParam("viewId",viewId)
    .queryParam("jsonRequest",jsonRequest);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public IView deleteStickyFilter(ViewId viewId,String filterId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteStickyFilter"))
    .queryParam("viewId",viewId)
    .queryParam("filterId",filterId);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public void closeView(ViewId viewId,ViewCloseAction closeAction){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/closeView"))
    .queryParam("viewId",viewId)
    .queryParam("closeAction",closeAction);

  restTemplate.put(builder.toUriString(), null);
}


public ViewLayout getViewLayout(WindowId windowId,JSONViewDataType viewDataType,ViewProfileId profileId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewLayout"))
    .queryParam("windowId",windowId)
    .queryParam("viewDataType",viewDataType)
    .queryParam("profileId",profileId);
  ViewLayout aux = restTemplate.getForObject(builder.toUriString(), ViewLayout.class);

 return aux;
}


public List<ViewProfile> getAvailableProfiles(WindowId windowId,JSONViewDataType viewDataType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAvailableProfiles"))
    .queryParam("windowId",windowId)
    .queryParam("viewDataType",viewDataType);
  List<ViewProfile> aux = restTemplate.getForObject(builder.toUriString(), List<ViewProfile>.class);

 return aux;
}


}