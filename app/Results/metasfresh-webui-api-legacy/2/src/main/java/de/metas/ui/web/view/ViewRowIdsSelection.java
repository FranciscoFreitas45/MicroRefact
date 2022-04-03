package de.metas.ui.web.view;
 import java.util.Set;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
import lombok.Value;
@Value
public class ViewRowIdsSelection {

 private  ViewId viewId;

 private  DocumentIdsSelection rowIds;


public ViewRowIdsSelection ofNullableStrings(String viewIdStr,Set<String> rowIdsStringSet){
    if (viewIdStr == null || viewIdStr.isEmpty()) {
        return null;
    }
    final ViewId viewId = ViewId.ofViewIdString(viewIdStr);
    final DocumentIdsSelection rowIds = DocumentIdsSelection.ofStringSet(rowIdsStringSet);
    return new ViewRowIdsSelection(viewId, rowIds);
}


public ViewRowIdsSelection of(ViewId viewId,DocumentIdsSelection rowIds){
    return new ViewRowIdsSelection(viewId, rowIds);
}


public boolean isEmpty(){
    return rowIds.isEmpty();
}


}