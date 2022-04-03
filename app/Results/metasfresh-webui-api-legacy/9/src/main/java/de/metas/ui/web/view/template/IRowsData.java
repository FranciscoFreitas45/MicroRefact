package de.metas.ui.web.view.template;
 import java.util.Collection;
import java.util.Map;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
public interface IRowsData {


public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs)
;

public Map<DocumentId,T> getDocumentId2AllRows(){
    return RowsDataTool.extractAllRows(getDocumentId2TopLevelRows().values());
}
;

public void invalidateAll()
;

public int size(){
    return getDocumentId2TopLevelRows().size();
}
;

public T getById(DocumentId rowId){
    final T row = getDocumentId2AllRows().get(rowId);
    if (row == null) {
        throw new EntityNotFoundException("Row not found").appendParametersToMessage().setParameter("rowId", rowId);
    }
    return row;
}
;

public Collection<T> getAllRows(){
    return getDocumentId2AllRows().values();
}
;

public void invalidate(DocumentIdsSelection rowIds){
    invalidateAll();
}
;

public Collection<T> getTopLevelRows(){
    return getDocumentId2TopLevelRows().values();
}
;

public Map<DocumentId,T> getDocumentId2TopLevelRows()
;

}