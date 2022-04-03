package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class IViewsRepositoryImpl implements IViewsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<IView> getViews(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViews"))
  List<IView> aux = restTemplate.getForObject(builder.toUriString(), List<IView>.class);

 return aux;
}


public Object stream(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stream"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object map(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/map"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public T getView(ViewId viewId,Class<T> type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getView"))
    .queryParam("viewId",viewId)
    .queryParam("type",type);
  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}