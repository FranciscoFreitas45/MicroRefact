package de.metas.ui.web.view.event;
 import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public class ViewChanges {

 private  ViewId viewId;

 private  boolean fullyChanged;

 private  Set<DocumentId> changedRowIds;


public DocumentIdsSelection getChangedRowIds(){
    final boolean fullyChanged = this.fullyChanged;
    final Set<DocumentId> changedRowIds = this.changedRowIds;
    if (fullyChanged) {
        return DocumentIdsSelection.ALL;
    } else if (changedRowIds == null || changedRowIds.isEmpty()) {
        return DocumentIdsSelection.EMPTY;
    } else {
        return DocumentIdsSelection.of(changedRowIds);
    }
}


public void addChangedRowId(DocumentId rowId){
    if (changedRowIds == null) {
        changedRowIds = new HashSet<>();
    }
    changedRowIds.add(rowId);
}


public void collectFrom(ViewChanges changes){
    if (changes.isFullyChanged()) {
        fullyChanged = true;
    }
    if (changes.changedRowIds != null && !changes.changedRowIds.isEmpty()) {
        if (changedRowIds == null) {
            changedRowIds = new HashSet<>();
        }
        changedRowIds.addAll(changes.changedRowIds);
    }
}


public boolean hasChanges(){
    if (fullyChanged) {
        return true;
    }
    return changedRowIds != null && !changedRowIds.isEmpty();
}


public void setFullyChanged(){
    fullyChanged = true;
}


public void addChangedRowIds(Collection<DocumentId> rowIds){
    if (changedRowIds == null) {
        changedRowIds = new HashSet<>();
    }
    changedRowIds.addAll(rowIds);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("viewId", viewId).add("fullyChanged", fullyChanged ? Boolean.TRUE : null).add("changedRowIds", changedRowIds).toString();
}


public ViewId getViewId(){
    return viewId;
}


public boolean isFullyChanged(){
    return fullyChanged;
}


}