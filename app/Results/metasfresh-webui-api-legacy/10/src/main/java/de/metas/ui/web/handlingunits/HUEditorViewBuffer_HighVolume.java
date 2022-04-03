package de.metas.ui.web.handlingunits;
 import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.adempiere.util.lang.Mutables;
import org.adempiere.util.lang.SynchronizedMutable;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUIdsFilterHelper.HUIdsFilterData;
import de.metas.ui.web.view.ViewEvaluationCtx;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewRowIdsOrderedSelection;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.collections.IteratorUtils;
import de.metas.util.collections.PagedIterator.PageFetcher;
import lombok.NonNull;
public class HUEditorViewBuffer_HighVolume implements HUEditorViewBuffer{

 private  int HIGHVOLUME_THRESHOLD;

 private  int STREAM_ALL_MAX_SIZE_ALLOWED;

 private  ViewEvaluationCtx viewEvaluationCtx;

 private  HUEditorViewRepository huEditorRepo;

 private  DocumentFilterList stickyFilters;

 private  Supplier<ViewRowIdsOrderedSelection> defaultSelectionFactory;

 private  SynchronizedMutable<ViewRowIdsOrderedSelection> defaultSelectionRef;

 private  ConcurrentHashMap<DocumentQueryOrderByList,ViewRowIdsOrderedSelection> selectionsByOrderBys;

 private  CCache<DocumentId,HUEditorRow> cache_huRowsById;


public PageFetcher<HuId> huIdsPageFetcher(DocumentQueryOrderByList orderBys){
    final ViewEvaluationCtx viewEvalCtx = getViewEvaluationCtx();
    final ViewRowIdsOrderedSelection selection = getSelection(orderBys);
    return (firstRow, maxRows) -> huEditorRepo.retrieveHUIdsPage(viewEvalCtx, selection, firstRow, maxRows);
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds){
    return huEditorRepo.buildSqlWhereClause(getDefaultSelection(), rowIds);
}


public ViewRowIdsOrderedSelection getDefaultSelection(){
    return defaultSelectionRef.computeIfNull(defaultSelectionFactory);
}


public boolean isHighVolume(DocumentFilterList stickyFilters){
    final HUIdsFilterData huIdsFilterData = HUIdsFilterHelper.extractFilterDataOrNull(stickyFilters);
    if (huIdsFilterData == null) {
        return true;
    }
    final Set<HuId> huIds = huIdsFilterData.getInitialHUIds();
    if (huIds == null) {
        // null means no restrictions, so we might have a lot of HUs
        // high volume
        return true;
    } else if (huIds.isEmpty()) {
        // no HUs will be allowed
        // not high volume
        return false;
    } else {
        // consider high volume if it's above give threshold
        return huIds.size() >= HIGHVOLUME_THRESHOLD;
    }
}


@Override
public Stream<HUEditorRow> streamPage(int firstRow,int pageLength,HUEditorRowFilter filter,ViewRowsOrderBy orderBys){
    final Iterator<HUEditorRowId> rowIds = streamHUIdsByPage(firstRow, pageLength, orderBys.toDocumentQueryOrderByList()).map(HUEditorRowId::ofTopLevelHU).iterator();
    return HUEditorRowsPagedLoadingIterator.builder().huEditorRepo(huEditorRepo).cache(cache_huRowsById).rowIds(rowIds).filter(filter).build().stream();
}


@Override
public boolean removeHUIds(Collection<HuId> huIdsToRemove){
    if (huIdsToRemove == null || huIdsToRemove.isEmpty()) {
        return false;
    }
    final DocumentIdsSelection rowIdsToRemove = HUEditorRowId.rowIdsFromTopLevelHuIds(huIdsToRemove);
    cache_huRowsById.removeAll(rowIdsToRemove.toSet());
    return changeSelection(defaultSelection -> huEditorRepo.removeRowIdsFromSelection(defaultSelection, rowIdsToRemove));
}


public boolean changeSelection(UnaryOperator<ViewRowIdsOrderedSelection> mapper){
    final ViewRowIdsOrderedSelection defaultSelectionOld = defaultSelectionRef.getValue();
    // make sure it's not null (might be null if it was invalidated)
    defaultSelectionRef.computeIfNull(defaultSelectionFactory);
    final ViewRowIdsOrderedSelection defaultSelectionNew = defaultSelectionRef.compute(mapper);
    // invalidate all the other selections, let it recompute when needed
    selectionsByOrderBys.clear();
    return !Objects.equals(defaultSelectionOld, defaultSelectionNew);
}


@Override
public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter){
    final ViewRowIdsOrderedSelection defaultSelection = getDefaultSelection();
    if (defaultSelection.getSize() > STREAM_ALL_MAX_SIZE_ALLOWED) {
        throw new UnsupportedOperationException("Streaming all rows when selection is bigger than " + STREAM_ALL_MAX_SIZE_ALLOWED + " is not allowed");
    }
    final JSONOptions jsonOpts = JSONOptions.newInstance();
    final ViewRowsOrderBy orderBys = ViewRowsOrderBy.of(defaultSelection.getOrderBys(), jsonOpts);
    return streamPage(0, STREAM_ALL_MAX_SIZE_ALLOWED, filter, orderBys).flatMap(HUEditorRow::streamRecursive).map(HUEditorRow::cast).filter(HUEditorRowFilters.toPredicate(filter));
}


public Stream<HUEditorRow> streamByIds(HUEditorRowFilter filter){
    final Stream<HUEditorRowId> huEditorRowIds;
    final ImmutableSet<HUEditorRowId> onlyRowIds = filter.getOnlyRowIds();
    if (onlyRowIds.isEmpty()) {
        final DocumentQueryOrderByList defaultOrderBys = getDefaultSelection().getOrderBys();
        huEditorRowIds = streamHUIdsByPage(0, Integer.MAX_VALUE, defaultOrderBys).map(HUEditorRowId::ofTopLevelHU);
    } else {
        huEditorRowIds = onlyRowIds.stream();
    }
    return HUEditorRowsPagedLoadingIterator.builder().huEditorRepo(huEditorRepo).cache(cache_huRowsById).rowIds(huEditorRowIds.iterator()).filter(filter).build().stream();
}


public ViewEvaluationCtx getViewEvaluationCtx(){
    return viewEvaluationCtx;
}


@Override
public boolean addHUIds(Collection<HuId> huIdsToAdd){
    if (huIdsToAdd == null || huIdsToAdd.isEmpty()) {
        return false;
    }
    final DocumentIdsSelection rowIdsToAdd = HUEditorRowId.rowIdsFromTopLevelHuIds(huIdsToAdd);
    if (rowIdsToAdd.isEmpty()) {
        return false;
    }
    return changeSelection(defaultSelection -> huEditorRepo.addRowIdsToSelection(defaultSelection, rowIdsToAdd));
}


@Override
public Stream<HUEditorRow> streamByIdsExcludingIncludedRows(HUEditorRowFilter filter){
    return streamByIds(filter);
}


public ViewRowIdsOrderedSelection getSelection(DocumentQueryOrderByList orderBys){
    final ViewRowIdsOrderedSelection defaultSelection = getDefaultSelection();
    if (orderBys == null || orderBys.isEmpty()) {
        return defaultSelection;
    }
    if (DocumentQueryOrderByList.equals(defaultSelection.getOrderBys(), orderBys)) {
        return defaultSelection;
    }
    return selectionsByOrderBys.computeIfAbsent(orderBys, k -> huEditorRepo.createSelectionFromSelection(getViewEvaluationCtx(), defaultSelection, orderBys));
}


@Override
public long size(){
    return getDefaultSelection().getSize();
}


@Override
public void invalidateAll(){
    defaultSelectionRef.computeIfNotNull(defaultSelection -> {
        huEditorRepo.deleteSelection(defaultSelection);
        return null;
    });
    huEditorRepo.invalidateCache();
    cache_huRowsById.reset();
}


@Override
public HUEditorRow getById(DocumentId rowId){
    final HUEditorRowId huRowId = HUEditorRowId.ofDocumentId(rowId);
    final HUEditorRowId topLevelRowId = huRowId.toTopLevelRowId();
    final HuId topLevelHUId = topLevelRowId.getTopLevelHUId();
    final HUEditorRow topLevelRow = cache_huRowsById.getOrLoad(topLevelRowId.toDocumentId(), () -> huEditorRepo.retrieveForHUId(topLevelHUId));
    if (topLevelRowId.equals(huRowId)) {
        return topLevelRow;
    } else {
        return topLevelRow.getIncludedRowById(rowId).orElseThrow(() -> new EntityNotFoundException("No row found for " + rowId).setParameter("topLevelRowId", topLevelRowId));
    }
}


@Override
public DocumentFilterList getStickyFilters(){
    return stickyFilters;
}


@Override
public ViewId getViewId(){
    return getDefaultSelection().getViewId();
}


public Stream<HuId> streamHUIdsByPage(int firstRow,int maxRows,DocumentQueryOrderByList orderBys){
    return IteratorUtils.<HuId>newPagedIterator().firstRow(firstRow).maxRows(maxRows).pageSize(// fetch 100items/chunk
    100).pageFetcher(huIdsPageFetcher(orderBys)).build().stream();
}


@Override
public boolean containsAnyOfHUIds(Collection<HuId> huIdsToCheck){
    final DocumentIdsSelection rowIds = HUEditorRowId.rowIdsFromTopLevelHuIds(huIdsToCheck);
    return huEditorRepo.containsAnyOfRowIds(getDefaultSelection(), rowIds);
}


}