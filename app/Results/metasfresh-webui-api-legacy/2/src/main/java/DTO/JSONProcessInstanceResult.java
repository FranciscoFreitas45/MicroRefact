package DTO;
 import java.io.Serializable;
import java.util.Set;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.logging.LogManager;
import de.metas.ui.web.process.ProcessInstanceResult;
import de.metas.ui.web.process.ProcessInstanceResult.DisplayQRCodeAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenIncludedViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenReportAction;
import de.metas.ui.web.process.ProcessInstanceResult.OpenSingleDocument;
import de.metas.ui.web.process.ProcessInstanceResult.OpenViewAction;
import de.metas.ui.web.process.ProcessInstanceResult.ResultAction;
import de.metas.ui.web.process.ProcessInstanceResult.SelectViewRowsAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
public class JSONProcessInstanceResult implements Serializable{

 private  Logger logger;

 private  String pinstanceId;

 private  String summary;

 private  boolean error;

 private  JSONResultAction action;

 private  String type;

 private  String filename;

 private  String contentType;

 private  WindowId windowId;

 private  String viewId;

 private  String profileId;

 private  boolean modalOverlay;

 private  WindowId windowId;

 private  String viewId;

 private  JSONViewDataType viewType;

 private  String profileId;

 private  WindowId windowId;

 private  String documentId;

 private  boolean modal;

 private  boolean advanced;

 private  WindowId windowId;

 private  String viewId;

 private  Set<String> rowIds;

 private  String code;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public JSONProcessInstanceResult of(ProcessInstanceResult result){
    return new JSONProcessInstanceResult(result);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("result",result);
JSONProcessInstanceResult aux = restTemplate.getForObject(builder.toUriString(),JSONProcessInstanceResult.class);
return aux;
}


}