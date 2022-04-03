package de.metas.ui.web.handlingunits;
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
public class HUEditorViewBuffer_FullyCached implements HUEditorViewBuffer{

 private  ViewId viewId;

 private  HUEditorViewRepository huEditorRepo;

 private  DocumentFilterList stickyFiltersWithoutHUIdsFilter;

 private  HUIdsFilterData huIdsFilterData;

 private  ExtendedMemorizingSupplier<CopyOnWriteArraySet<HuId>> huIdsSupplier;

 private  ExtendedMemorizingSupplier<IndexedHUEditorRows> rowsSupplier;

 private  DocumentQueryOrderByList defaultOrderBys;

 private  ImmutableList<HUEditorRow> rows;

 private  ImmutableMap<DocumentId,HUEditorRow> allRowsById;

 private  ImmutableMap<HUEditorRowId,HUEditorRowId> rowId2parentId;


public void buildRowId2ParentIdMap(ImmutableMap.Builder<HUEditorRowId,HUEditorRowId> rowId2parentId,HUEditorRow parentRow){
    final HUEditorRowId parentId = parentRow.getHURowId();
    parentRow.getIncludedRows().forEach(includedRow -> rowId2parentId.put(includedRow.getHURowId(), parentId));
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds){
    final DocumentIdsSelection rowIdsEffective = getRows().streamByIdsExcludingIncludedRows(HUEditorRowFilter.onlyRowIds(rowIds)).map(HUEditorRow::getId).collect(DocumentIdsSelection.toDocumentIdsSelection());
    final Set<Integer> huIds = huEditorRepo.getRowIdsConverter().convertToRecordIds(rowIdsEffective);
    // NOTE: accept it even if is empty. In case it's empty, we will return something like M_HU_ID in (-1)
    // same this is happening for the others HUEditorViewBuffer implementation
    // see https://github.com/metasfresh/metasfresh-webui-api/issues/764
    final String sqlKeyColumnNameFK = I_M_HU.Table_Name + "." + I_M_HU.COLUMNNAME_M_HU_ID;
    return sqlKeyColumnNameFK + " IN " + DB.buildSqlList(huIds);
}


public IndexedHUEditorRows retrieveHUEditorRows(){
    final Set<HuId> huIds = getHUIds();
    final List<HUEditorRow> rows = huEditorRepo.retrieveHUEditorRows(huIds, HUEditorRowFilter.ALL);
    return new IndexedHUEditorRows(rows);
}


public void indexByIdRecursively(ImmutableMap.Builder<DocumentId,HUEditorRow> collector,HUEditorRow row){
    collector.put(row.getId(), row);
    row.getIncludedRows().forEach(includedRow -> indexByIdRecursively(collector, includedRow));
}


public HUIdsFilterData getHUIdsFilterData(){
    return huIdsFilterData;
}


@Override
public Stream<HUEditorRow> streamPage(int firstRow,int pageLength,HUEditorRowFilter filter,ViewRowsOrderBy orderBys){
    final ViewRowsOrderBy orderBysEffective = !orderBys.isEmpty() ? orderBys : orderBys.withOrderBys(defaultOrderBys);
    Stream<HUEditorRow> stream = getRows().stream().skip(firstRow).limit(pageLength).filter(HUEditorRowFilters.toPredicate(filter));
    final Comparator<HUEditorRow> comparator = orderBysEffective.toComparatorOrNull();
    if (comparator != null) {
        stream = stream.sorted(comparator);
    }
    return stream;
}


public Stream<HUEditorRow> streamRecursive(){
    return stream().flatMap(HUEditorRow::streamRecursive).map(HUEditorRow::cast);
}


@Override
public boolean removeHUIds(Collection<HuId> huIdsToRemove){
    if (huIdsToRemove == null || huIdsToRemove.isEmpty()) {
        return false;
    }
    getHUIdsFilterData().shallNotHUIds(huIdsToRemove);
    return getHUIds().removeAll(huIdsToRemove);
}


public CopyOnWriteArraySet<HuId> getHUIds(){
    return huIdsSupplier.get();
}


public IndexedHUEditorRows getRows(){
    return rowsSupplier.get();
}


@Override
public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter){
    return getRows().streamRecursive().filter(HUEditorRowFilters.toPredicate(filter));
}


@Override
public boolean addHUIds(Collection<HuId> huIdsToAdd){
    if (huIdsToAdd.isEmpty()) {
        return false;
    }
    getHUIdsFilterData().mustHUIds(huIdsToAdd);
    return getHUIds().addAll(huIdsToAdd);
}


public Stream<HUEditorRow> streamByIdsExcludingIncludedRows(HUEditorRowFilter filter){
    final ImmutableSet<HUEditorRowId> onlyRowIds = filter.getOnlyRowIds();
    if (onlyRowIds.isEmpty()) {
        return allRowsById.values().stream().filter(HUEditorRowFilters.toPredicate(filter));
    } else {
        return onlyRowIds.stream().distinct().filter(rowId -> !isRowIdIncluded(onlyRowIds, rowId)).map(rowId -> allRowsById.get(rowId.toDocumentId())).filter(Objects::nonNull).filter(HUEditorRowFilters.toPredicate(filter));
    }
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


@Override
public void invalidateAll(){
    huIdsSupplier.forget();
    huEditorRepo.invalidateCache();
    rowsSupplier.forget();
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


public ImmutableMap<DocumentId,HUEditorRow> buildRowsByIdMap(List<HUEditorRow> rows){
    if (rows.isEmpty()) {
        return ImmutableMap.of();
    }
    final ImmutableMap.Builder<DocumentId, HUEditorRow> rowsById = ImmutableMap.builder();
    rows.forEach(row -> indexByIdRecursively(rowsById, row));
    return rowsById.build();
}


@Override
public ViewId getViewId(){
    return viewId;
}


@Override
public DocumentFilterList getStickyFilters(){
    final DocumentFilter huIdsFilter = HUIdsFilterHelper.createFilter(huIdsFilterData.copy());
    return stickyFiltersWithoutHUIdsFilter.mergeWith(huIdsFilter);
}


@Override
public boolean containsAnyOfHUIds(Collection<HuId> huIdsToCheck){
    if (huIdsToCheck == null || huIdsToCheck.isEmpty()) {
        return false;
    }
    return !Collections.disjoint(getHUIds(), huIdsToCheck);
}


}