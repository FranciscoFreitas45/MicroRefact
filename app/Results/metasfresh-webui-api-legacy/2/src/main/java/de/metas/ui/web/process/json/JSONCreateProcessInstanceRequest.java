package de.metas.ui.web.process.json;
 import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.Data;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONCreateProcessInstanceRequest {

@JsonProperty("processId")
 private  String processIdStr;

@JsonIgnore
 private  ProcessId processId;

@JsonProperty("windowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  WindowId windowId;

@JsonProperty("documentId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String documentId;

@JsonProperty("tabId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String tabId;

@JsonProperty("rowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String rowId;

@JsonIgnore
 private  DocumentPath singleDocumentPath;

@JsonProperty("selectedTab")
 private  JSONSelectedIncludedTab selectedTab;

@JsonIgnore
 private  List<DocumentPath> selectedIncludedDocumentPaths;

@JsonProperty("viewId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String viewIdStr;

@JsonProperty("viewDocumentIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<String> viewDocumentIdsStrings;

@JsonIgnore
 private  ViewRowIdsSelection viewRowIdsSelection;

@JsonProperty("parentViewId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String parentViewIdStr;

@JsonProperty("parentViewSelectedIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<String> parentViewSelectedIdsStrings;

@JsonIgnore
 private  ViewRowIdsSelection parentViewRowIdsSelection;

@JsonProperty("childViewId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String childViewIdStr;

@JsonProperty("childViewSelectedIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<String> childViewSelectedIdsStrings;

@JsonIgnore
 private  ViewRowIdsSelection childViewRowIdsSelection;

@JsonProperty("tabId")
 private  String tabId;

@JsonProperty("rowIds")
 private  List<String> rowIds;


public ViewRowIdsSelection getViewRowIdsSelection(){
    return viewRowIdsSelection;
}


public ViewRowIdsSelection getParentViewRowIdsSelection(){
    return parentViewRowIdsSelection;
}


public List<DocumentPath> createSelectedIncludedDocumentPaths(WindowId windowId,String documentIdStr,JSONSelectedIncludedTab selectedTab){
    if (windowId == null || Check.isEmpty(documentIdStr, true) || selectedTab == null) {
        return ImmutableList.of();
    }
    final DocumentId documentId = DocumentId.of(documentIdStr);
    final DetailId selectedTabId = DetailId.fromJson(selectedTab.getTabId());
    return selectedTab.getRowIds().stream().map(DocumentId::of).map(rowId -> DocumentPath.includedDocumentPath(windowId, documentId, selectedTabId, rowId)).collect(ImmutableList.toImmutableList());
}


public DocumentPath createDocumentPathOrNull(WindowId windowId,String documentId,String tabId,String rowIdStr){
    if (windowId != null && !Check.isEmpty(documentId)) {
        if (Check.isEmpty(tabId) && Check.isEmpty(rowIdStr)) {
            return DocumentPath.rootDocumentPath(windowId, documentId);
        } else {
            return DocumentPath.includedDocumentPath(windowId, documentId, tabId, rowIdStr);
        }
    }
    return null;
}


public ProcessId getProcessId(){
    return processId;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("processId", processId).add("documentType", windowId).add("documentId", documentId).add("tabId", tabId).add("rowId", rowId).add("viewRowIdsSelection", viewRowIdsSelection).add("parentViewRowIdsSelection", parentViewRowIdsSelection).add("childViewRowIdsSelection", childViewRowIdsSelection).add("selectedTab", selectedTab).toString();
}


public List<DocumentPath> getSelectedIncludedDocumentPaths(){
    return selectedIncludedDocumentPaths;
}


public DocumentPath getSingleDocumentPath(){
    return singleDocumentPath;
}


public ViewRowIdsSelection getChildViewRowIdsSelection(){
    return childViewRowIdsSelection;
}


}