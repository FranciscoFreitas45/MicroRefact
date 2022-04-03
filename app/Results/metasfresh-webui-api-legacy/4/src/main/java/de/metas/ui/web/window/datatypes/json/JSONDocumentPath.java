package de.metas.ui.web.window.datatypes.json;
 import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentPath {

@JsonProperty("windowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  WindowId windowId;

@JsonProperty("processId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  ProcessId processId;

@JsonProperty("viewId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  ViewId viewId;

@JsonProperty("documentId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentId documentId;

@JsonProperty("tabId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DetailId tabId;

@JsonProperty("rowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentId rowId;

@JsonProperty("fieldName")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String fieldName;


public boolean isWindow(){
    return windowId != null;
}


public DocumentPath toDocumentPath(){
    final DocumentPath.Builder builder = DocumentPath.builder();
    // Window
    if (windowId != null) {
        builder.setDocumentType(windowId);
    } else // Process
    if (processId != null) {
        builder.setDocumentType(DocumentType.Process, processId.toDocumentId());
    } else {
        throw new AdempiereException("Cannot identify the document type because it's not window nor process").setParameter("documentPath", this);
    }
    return builder.setDocumentId(documentId).setDetailId(tabId).setRowId(rowId).build();
}


public DocumentPath toDocumentPathOrNull(JSONDocumentPath jsonDocumentPath){
    return jsonDocumentPath != null ? jsonDocumentPath.toDocumentPath() : null;
}


public boolean isView(){
    return viewId != null;
}


public JSONDocumentPath ofWindowDocumentPath(DocumentPath documentPath,String fieldName){
    final JSONDocumentPathBuilder builder = builder().windowId(documentPath.getWindowId()).documentId(documentPath.getDocumentId()).fieldName(// optional
    fieldName);
    if (documentPath.isRootDocument()) {
        return builder.build();
    } else if (documentPath.isSingleIncludedDocument()) {
        return builder.tabId(documentPath.getDetailId()).rowId(documentPath.getSingleRowId()).build();
    } else {
        throw new IllegalArgumentException("Cannot convert " + documentPath + " to JSON");
    }
}


public JSONDocumentPath newWindowRecord(WindowId windowId){
    return builder().windowId(windowId).documentId(DocumentId.NEW).build();
}


public boolean isProcess(){
    return processId != null;
}


public DocumentPath toSingleDocumentPath(){
    if (windowId != null) {
        return DocumentPath.singleWindowDocumentPath(windowId, documentId, tabId, rowId);
    } else if (processId != null) {
        return DocumentPath.rootDocumentPath(DocumentType.Process, processId.toDocumentId(), documentId);
    } else {
        throw new IllegalStateException("Cannot create single document path from " + this);
    }
}


}