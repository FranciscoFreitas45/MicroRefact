package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewRowIdsSelectionImpl implements ViewRowIdsSelection{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public ViewRowIdsSelection of(ViewId viewId,DocumentIdsSelection rowIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("viewId",viewId)
    .queryParam("rowIds",rowIds);
  ViewRowIdsSelection aux = restTemplate.getForObject(builder.toUriString(), ViewRowIdsSelection.class);

 return aux;
}


public ViewRowIdsSelection ofNullableStrings(String viewIdStr,Set<String> rowIdsStringSet){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofNullableStrings"))
    .queryParam("viewIdStr",viewIdStr)
    .queryParam("rowIdsStringSet",rowIdsStringSet);
  ViewRowIdsSelection aux = restTemplate.getForObject(builder.toUriString(), ViewRowIdsSelection.class);

 return aux;
}


public Object getRowIds(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRowIds"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getViewId(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getViewId"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}