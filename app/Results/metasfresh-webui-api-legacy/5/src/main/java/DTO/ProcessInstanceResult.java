package DTO;
 import java.io.File;
import org.compiere.util.Util;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
public class ProcessInstanceResult {

 private  DocumentId instanceId;

 private  String summary;

 private  boolean error;

 private  ResultAction action;

 private  String filename;

 private  String contentType;

 private  File tempFile;

 private  ViewId viewId;

 private  ViewProfileId profileId;

 private  boolean modalOverlay;

 private  ViewId viewId;

 private  ViewProfileId profileId;

 private  CreateViewRequest createViewRequest;

 private  DocumentPath documentPath;

 private  boolean modal;

 private  ViewId viewId;

 private  DocumentIdsSelection rowIds;

 private  String code;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public byte[] getReportData(){
    return Util.readBytes(tempFile);
}


public T getAction(Class<T> actionType){
    final ResultAction action = getAction();
    if (action == null) {
        throw new IllegalStateException("No action defined");
    }
    if (!actionType.isAssignableFrom(action.getClass())) {
        throw new IllegalStateException("Action is not of type " + actionType + " but " + action.getClass());
    }
    @SuppressWarnings("unchecked")
    final T actionCasted = (T) action;
    return actionCasted;
}


public ProcessInstanceResultBuilder builder(DocumentId instanceId){
    return new ProcessInstanceResultBuilder().instanceId(instanceId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

.queryParam("instanceId",instanceId);
ProcessInstanceResultBuilder aux = restTemplate.getForObject(builder.toUriString(),ProcessInstanceResultBuilder.class);
return aux;
}


}