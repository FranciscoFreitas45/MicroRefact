package DTO;
 import java.util.Set;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.Value;
public class ViewRowIdsSelection {

 private  ViewId viewId;

 private  DocumentIdsSelection rowIds;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public boolean isEmpty(){
    return rowIds.isEmpty();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public ViewRowIdsSelection of(ViewId viewId,DocumentIdsSelection rowIds){
    return new ViewRowIdsSelection(viewId, rowIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("viewId",viewId);
.queryParam("rowIds",rowIds);
ViewRowIdsSelection aux = restTemplate.getForObject(builder.toUriString(),ViewRowIdsSelection.class);
return aux;
}


public ViewRowIdsSelection ofNullableStrings(String viewIdStr,Set<String> rowIdsStringSet){
    if (viewIdStr == null || viewIdStr.isEmpty()) {
        return null;
    }
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    final DocumentIdsSelection rowIds = DocumentIdsSelection.ofStringSet(rowIdsStringSet);
    return new ViewRowIdsSelection(viewId, rowIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofNullableStrings"))

.queryParam("viewIdStr",viewIdStr);
.queryParam("rowIdsStringSet",rowIdsStringSet);
ViewRowIdsSelection aux = restTemplate.getForObject(builder.toUriString(),ViewRowIdsSelection.class);
return aux;
}


}