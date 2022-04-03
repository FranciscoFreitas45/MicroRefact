package de.metas.ui.web.handlingunits;
 import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import de.metas.handlingunits.HuId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.NonNull;
public interface HUEditorViewBuffer {


public String getSqlWhereClause(DocumentIdsSelection rowIds)
;

public Stream<HUEditorRow> streamPage(int firstRow,int pageLength,HUEditorRowFilter filter,ViewRowsOrderBy orderBys)
;

public boolean removeHUIds(Collection<HuId> huIdsToRemove)
;

public Optional<HUEditorRow> getParentRowByChildIdOrNull(DocumentId childId){
    final HUEditorRowId childRowId = HUEditorRowId.ofDocumentId(childId);
    final HUEditorRowId topLevelRowId = childRowId.toTopLevelRowId();
    final HUEditorRow topLevelRow = getById(topLevelRowId.toDocumentId());
    return topLevelRow.streamRecursive().map(HUEditorRow::cast).filter(row -> row.hasDirectChild(childId)).findFirst();
}
;

public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter)
;

public boolean addHUIds(Collection<HuId> huIdsToAdd)
;

public Stream<HUEditorRow> streamByIdsExcludingIncludedRows(HUEditorRowFilter filter)
;

public boolean matchesAnyRowRecursive(HUEditorRowFilter filter){
    return streamAllRecursive(filter).findAny().isPresent();
}
;

public long size()
;

public void invalidateAll()
;

public HUEditorRow getById(DocumentId rowId)
;

public ViewId getViewId()
;

public DocumentFilterList getStickyFilters()
;

public boolean containsAnyOfHUIds(Collection<HuId> huIdsToCheck)
;

}