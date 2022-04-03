package de.metas.ui.web.process.json;
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
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONProcessInstanceResult implements Serializable{

 private  Logger logger;

@JsonProperty("pinstanceId")
 private  String pinstanceId;

@JsonProperty("summary")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String summary;

@JsonProperty("error")
 private  boolean error;

@JsonProperty("action")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONResultAction action;

@JsonProperty("type")
 private  String type;

 private  String filename;

 private  String contentType;

 private  WindowId windowId;

 private  String viewId;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String profileId;

 private  boolean modalOverlay;

 private  WindowId windowId;

 private  String viewId;

 private  JSONViewDataType viewType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String profileId;

 private  WindowId windowId;

 private  String documentId;

 private  boolean modal;

 private  boolean advanced;

 private  WindowId windowId;

 private  String viewId;

 private  Set<String> rowIds;

 private  String code;


public JSONProcessInstanceResult of(ProcessInstanceResult result){
    return new JSONProcessInstanceResult(result);
}


public JSONResultAction toJSONResultAction(ResultAction resultAction){
    if (resultAction == null) {
        return null;
    } else if (resultAction instanceof OpenReportAction) {
        final OpenReportAction openReportAction = (OpenReportAction) resultAction;
        return new JSONOpenReportAction(openReportAction.getFilename(), openReportAction.getContentType());
    } else if (resultAction instanceof OpenViewAction) {
        final OpenViewAction openViewAction = (OpenViewAction) resultAction;
        return new JSONOpenViewAction(openViewAction.getViewId(), openViewAction.getProfileId(), openViewAction.isModalOverlay());
    } else if (resultAction instanceof OpenIncludedViewAction) {
        final OpenIncludedViewAction openIncludedViewAction = (OpenIncludedViewAction) resultAction;
        return new JSONOpenIncludedViewAction(openIncludedViewAction.getViewId(), openIncludedViewAction.getProfileId());
    } else if (resultAction instanceof OpenSingleDocument) {
        final OpenSingleDocument openDocumentAction = (OpenSingleDocument) resultAction;
        final DocumentPath documentPath = openDocumentAction.getDocumentPath();
        return new JSONOpenSingleDocumentAction(documentPath.getWindowId(), documentPath.getDocumentId().toJson(), openDocumentAction.isModal());
    } else if (resultAction instanceof SelectViewRowsAction) {
        final SelectViewRowsAction selectViewRowsAction = (SelectViewRowsAction) resultAction;
        return new JSONSelectViewRowsAction(selectViewRowsAction.getViewId(), selectViewRowsAction.getRowIds());
    } else if (resultAction instanceof DisplayQRCodeAction) {
        final DisplayQRCodeAction displayQRCodeAction = (DisplayQRCodeAction) resultAction;
        return new JSONDisplayQRCodeAction(displayQRCodeAction.getCode());
    } else {
        logger.warn("Unknown result action: {}. Ignoring it.", resultAction);
        return null;
    }
}


}