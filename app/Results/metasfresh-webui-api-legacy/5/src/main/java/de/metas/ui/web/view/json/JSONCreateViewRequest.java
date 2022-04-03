package de.metas.ui.web.view.json;
 import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.util.collections.CollectionUtils;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@lombok.Data
public class JSONCreateViewRequest {

@JsonProperty("documentType")
 private  WindowId windowId;

@JsonProperty("viewType")
 private  JSONViewDataType viewType;

@JsonProperty("profileId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  ViewProfileId profileId;

@JsonProperty("referencing")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  JSONReferencing referencing;

@JsonProperty("filters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilter> filters;

@JsonProperty("filterOnlyIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<Integer> filterOnlyIds;

@JsonProperty("queryFirstRow")
 private  int queryFirstRow;

@JsonProperty("queryPageLength")
 private  int queryPageLength;

@JsonProperty("documentType")
 private  String documentType;

@JsonProperty("documentIds")
 private  Set<String> documentIds;

@JsonProperty("tabId")
 private  String tabId;

@JsonProperty("rowIds")
 private  Set<String> rowIds;


public Set<DocumentPath> getReferencingDocumentPaths(){
    if (referencing == null) {
        return ImmutableSet.of();
    }
    final Set<String> documentIds = referencing.getDocumentIds();
    if (documentIds == null || documentIds.isEmpty()) {
        return ImmutableSet.of();
    }
    final WindowId windowId = WindowId.fromJson(referencing.getDocumentType());
    final String tabIdStr = referencing.getTabId();
    if (tabIdStr == null) {
        return documentIds.stream().map(id -> DocumentPath.rootDocumentPath(windowId, id)).collect(ImmutableSet.toImmutableSet());
    } else {
        final DocumentId documentId = DocumentId.of(CollectionUtils.singleElement(documentIds));
        final DetailId tabId = DetailId.fromJson(tabIdStr);
        final Set<String> rowIds = referencing.getRowIds();
        return rowIds.stream().map(DocumentId::of).map(rowId -> DocumentPath.includedDocumentPath(windowId, documentId, tabId, rowId)).collect(ImmutableSet.toImmutableSet());
    }
}


}