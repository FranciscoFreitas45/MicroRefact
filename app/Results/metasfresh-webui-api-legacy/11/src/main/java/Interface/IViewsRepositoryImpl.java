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


public void invalidateView(IView view){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/invalidateView"))
    .queryParam("view",view);

  restTemplate.put(builder.toUriString(), null);
}


public IView createView(CreateViewRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createView"))
    .queryParam("request",request);
  IView aux = restTemplate.getForObject(builder.toUriString(), IView.class);

 return aux;
}


public void notifyRecordChanged(String tableName,int recordId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/notifyRecordChanged"))
    .queryParam("tableName",tableName)
    .queryParam("recordId",recordId);

  restTemplate.put(builder.toUriString(), null);
}


}