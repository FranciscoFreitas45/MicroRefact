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


public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/notifyRecordsChanged"))
    .queryParam("recordRefs",recordRefs);

  restTemplate.put(builder.toUriString(), null);
}


}