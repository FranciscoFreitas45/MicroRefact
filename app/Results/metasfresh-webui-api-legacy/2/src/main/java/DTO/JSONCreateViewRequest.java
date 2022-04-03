package DTO;
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
public class JSONCreateViewRequest {

 private  WindowId windowId;

 private  JSONViewDataType viewType;

 private  ViewProfileId profileId;

 private  JSONReferencing referencing;

 private  List<JSONDocumentFilter> filters;

 private  Set<Integer> filterOnlyIds;

 private  int queryFirstRow;

 private  int queryPageLength;

 private  String documentType;

 private  Set<String> documentIds;

 private  String tabId;

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