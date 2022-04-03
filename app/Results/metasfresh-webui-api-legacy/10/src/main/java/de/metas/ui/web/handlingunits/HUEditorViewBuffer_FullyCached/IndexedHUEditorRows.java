package de.metas.ui.web.handlingunits.HUEditorViewBuffer_FullyCached;
 import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.compiere.util.DB;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper.HUIdsFilterData;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.NonNull;
public class IndexedHUEditorRows {

 private  ImmutableList<HUEditorRow> rows;

 private  ImmutableMap<DocumentId,HUEditorRow> allRowsById;

 private  ImmutableMap<HUEditorRowId,HUEditorRowId> rowId2parentId;


public Stream<HUEditorRow> streamByIdsExcludingIncludedRows(HUEditorRowFilter filter){
    final ImmutableSet<HUEditorRowId> onlyRowIds = filter.getOnlyRowIds();
    if (onlyRowIds.isEmpty()) {
        return allRowsById.values().stream().filter(HUEditorRowFilters.toPredicate(filter));
    } else {
        return onlyRowIds.stream().distinct().filter(rowId -> !isRowIdIncluded(onlyRowIds, rowId)).map(rowId -> allRowsById.get(rowId.toDocumentId())).filter(Objects::nonNull).filter(HUEditorRowFilters.toPredicate(filter));
    }
}


public void buildRowId2ParentIdMap(ImmutableMap.Builder<HUEditorRowId,HUEditorRowId> rowId2parentId,HUEditorRow parentRow){
    final HUEditorRowId parentId = parentRow.getHURowId();
    parentRow.getIncludedRows().forEach(includedRow -> rowId2parentId.put(includedRow.getHURowId(), parentId));
}


public boolean isRowIdIncluded(Set<HUEditorRowId> parentRowIds,HUEditorRowId childRowId){
    if (parentRowIds == null || parentRowIds.isEmpty()) {
        return false;
    }
    // Iterate child row's up-stream (up to the root row)
    HUEditorRowId currentChildId = childRowId;
    while (currentChildId != null) {
        final HUEditorRowId currentParentRowId = rowId2parentId.get(currentChildId);
        if (currentParentRowId == null) {
            // => not included (currentChildId is a top level row)
            return false;
        }
        if (parentRowIds.contains(currentParentRowId)) {
            // => included (current child is included in our parents list)
            return true;
        }
        currentChildId = currentParentRowId;
    }
    return false;
}


public long size(){
    return rows.size();
}


public HUEditorRow getById(DocumentId rowId){
    final HUEditorRow record = allRowsById.get(rowId);
    if (record == null) {
        throw new EntityNotFoundException("No document found for rowId=" + rowId);
    }
    return record;
}


public Stream<HUEditorRow> stream(){
    return rows.stream();
}


public void indexByIdRecursively(ImmutableMap.Builder<DocumentId,HUEditorRow> collector,HUEditorRow row){
    collector.put(row.getId(), row);
    row.getIncludedRows().forEach(includedRow -> indexByIdRecursively(collector, includedRow));
}


public Stream<HUEditorRow> streamRecursive(){
    return stream().flatMap(HUEditorRow::streamRecursive).map(HUEditorRow::cast);
}


public ImmutableMap<DocumentId,HUEditorRow> buildRowsByIdMap(List<HUEditorRow> rows){
    if (rows.isEmpty()) {
        return ImmutableMap.of();
    }
    final ImmutableMap.Builder<DocumentId, HUEditorRow> rowsById = ImmutableMap.builder();
    rows.forEach(row -> indexByIdRecursively(rowsById, row));
    return rowsById.build();
}


}