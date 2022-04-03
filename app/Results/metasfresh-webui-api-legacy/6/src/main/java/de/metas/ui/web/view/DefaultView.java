package de.metas.ui.web.view;
 import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.standard.FacetFilterViewCacheMap;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.DocumentSaveStatus;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
import de.metas.util.collections.IteratorUtils;
import de.metas.util.collections.PagedIterator.Page;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Stream;
public class DefaultView implements IEditableView{

 private  Logger logger;

@Getter
 private  IViewDataRepository viewDataRepository;

@Getter
 private  ViewId viewId;

 private  AtomicBoolean closed;

@Getter
 private  ViewId parentViewId;

@Getter
 private  DocumentId parentRowId;

@Getter
 private  JSONViewDataType viewType;

@Getter
 private  ViewProfileId profileId;

@Getter
 private  ImmutableSet<DocumentPath> referencingDocumentPaths;

@Getter
 private  ViewEvaluationCtx viewEvaluationCtx;

 private  ViewRowIdsOrderedSelectionsHolder selectionsRef;

 private  DocumentFilterDescriptorsProvider viewFilterDescriptors;

@Getter
 private  DocumentFilterList stickyFilters;

@Getter
 private  DocumentFilterList filters;

 private  DocumentFilterList _allFilters;

@Getter
 private  FacetFilterViewCacheMap facetFiltersCacheMap;

 private  String _toString;

 private  CCache<DocumentId,IViewRow> cache_rowsById;

 private  IViewInvalidationAdvisor viewInvalidationAdvisor;

 private  boolean refreshViewOnChangeEvents;

 private  ChangedRowIdsCollector changedRowIdsToCheck;

 private  HashSet<DocumentId> rowIds;

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  ViewProfileId profileId;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  IViewDataRepository viewDataRepository;

 private  LinkedHashMap<String,DocumentFilter> _stickyFiltersById;

 private  LinkedHashMap<String,DocumentFilter> _filtersById;

 private  boolean refreshViewOnChangeEvents;

 private  IViewInvalidationAdvisor viewInvalidationAdvisor;

 private  boolean applySecurityRestrictions;


public Builder refreshViewOnChangeEvents(boolean refreshViewOnChangeEvents){
    this.refreshViewOnChangeEvents = refreshViewOnChangeEvents;
    return this;
}


public Builder viewInvalidationAdvisor(IViewInvalidationAdvisor viewInvalidationAdvisor){
    this.viewInvalidationAdvisor = viewInvalidationAdvisor;
    return this;
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBy){
    assertNotClosed();
    checkChangedRows();
    final ViewEvaluationCtx evalCtx = getViewEvaluationCtx();
    final ViewRowIdsOrderedSelection orderedSelection = getOrderedSelection(orderBy.toDocumentQueryOrderByList());
    final List<IViewRow> rows = viewDataRepository.retrievePage(evalCtx, orderedSelection, firstRow, pageLength);
    // Add to cache
    rows.forEach(row -> cache_rowsById.put(row.getId(), row));
    return ViewResult.builder().view(this).firstRow(firstRow).pageLength(pageLength).orderBys(orderedSelection.getOrderBys()).rows(rows).columnInfos(extractViewResultColumns(rows)).build();
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    final DocumentId rowId = ctx.getRowId();
    final DocumentCollection documentsCollection = ctx.getDocumentsCollection();
    final DocumentPath documentPath = getById(rowId).getDocumentPath();
    return documentsCollection.forDocumentReadonly(documentPath, document -> document.getFieldLookupValues(fieldName));
}


public Builder addFiltersIfAbsent(Collection<DocumentFilter> filters){
    filters.forEach(filter -> _filtersById.putIfAbsent(filter.getFilterId(), filter));
    return this;
}


@Override
public void invalidateRowById(DocumentId rowId){
    cache_rowsById.remove(rowId);
}


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    final DocumentId rowId = ctx.getRowId();
    final DocumentCollection documentsCollection = ctx.getDocumentsCollection();
    final DocumentPath documentPath = getById(rowId).getDocumentPath();
    return documentsCollection.forDocumentReadonly(documentPath, document -> document.getFieldLookupValuesForQuery(fieldName, query));
}


public IViewDataRepository getViewDataRepository(){
    return viewDataRepository;
}


public DefaultView cast(IView view){
    return (DefaultView) view;
}


public ImmutableSet<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths == null ? ImmutableSet.of() : ImmutableSet.copyOf(referencingDocumentPaths);
}


public DocumentFilterList getFilters(){
    return _filtersById.isEmpty() ? DocumentFilterList.EMPTY : DocumentFilterList.ofList(_filtersById.values());
}


public IViewRow getOrRetrieveById(DocumentId rowId){
    checkChangedRows();
    return cache_rowsById.getOrLoad(rowId, () -> retrieveRowById(rowId));
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    assertNotClosed();
    return viewFilterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx, query);
}


public Builder addStickyFilter(DocumentFilter stickyFilter){
    if (stickyFilter == null) {
        return this;
    }
    if (_stickyFiltersById == null) {
        _stickyFiltersById = new LinkedHashMap<>();
    }
    _stickyFiltersById.put(stickyFilter.getFilterId(), stickyFilter);
    return this;
}


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    return viewDataRepository.getViewFilterDescriptors();
}


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return viewDataRepository.getViewFilterDescriptors();
}


public ViewResultColumn extractViewResultColumnOrNull(String fieldName,DocumentFieldWidgetType widgetType,List<IViewRow> rows){
    if (widgetType == DocumentFieldWidgetType.Integer) {
        return null;
    } else if (widgetType.isNumeric()) {
        final int maxPrecision = rows.stream().map(row -> row.getFieldValueAsBigDecimal(fieldName, BigDecimal.ZERO)).mapToInt(valueBD -> NumberUtils.stripTrailingDecimalZeros(valueBD).scale()).max().orElse(0);
        return ViewResultColumn.builder().fieldName(fieldName).widgetType(widgetType).maxPrecision(maxPrecision).build();
    } else {
        return null;
    }
}


public Builder setReferencingDocumentPaths(Set<DocumentPath> referencingDocumentPaths){
    this.referencingDocumentPaths = referencingDocumentPaths;
    return this;
}


public DocumentFilterList getAllFilters(){
    DocumentFilterList allFilters = _allFilters;
    if (allFilters == null) {
        _allFilters = allFilters = getFilters().mergeWith(getStickyFilters());
    }
    return allFilters;
}


@Override
public Stream<? extends IViewRow> streamByIds(DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        return Stream.empty();
    } else if (rowIds.isAll()) {
        assertNotClosed();
        checkChangedRows();
        final ViewEvaluationCtx evalCtx = getViewEvaluationCtx();
        final ViewRowIdsOrderedSelection orderedSelection = selectionsRef.getDefaultSelection();
        return IteratorUtils.<IViewRow>newPagedIterator().firstRow(0).maxRows(// MAX rows to fetch
        1000).pageSize(// fetch 100items/chunk
        100).pageFetcher((firstRow, pageSize) -> Page.ofRowsOrNull(viewDataRepository.retrievePage(evalCtx, orderedSelection, firstRow, pageSize))).build().stream();
    } else {
        // NOTE: we get/retrive one by one because we assume the "selected documents" were recently retrieved,
        // and the records recently retrieved have a big chance to be cached.
        return rowIds.stream().distinct().map(rowId -> {
            try {
                return getOrRetrieveById(rowId);
            } catch (final EntityNotFoundException e) {
                return null;
            }
        }).filter(Objects::nonNull);
    }
}


public Builder addStickyFilters(DocumentFilterList stickyFilters){
    if (stickyFilters == null || stickyFilters.isEmpty()) {
        return this;
    }
    stickyFilters.forEach(this::addStickyFilter);
    return this;
}


public void patchDocument(Document document,List<JSONDocumentChangedEvent> fieldChangeRequests){
    // 
    // Process changes and the save the document
    document.processValueChanges(fieldChangeRequests, ReasonSupplier.NONE);
    document.saveIfValidAndHasChanges();
    // 
    // Important: before allowing the document to be stored back in documents collection,
    // we need to make sure it's valid and saved.
    final DocumentValidStatus validStatus = document.getValidStatus();
    if (!validStatus.isValid()) {
        throw new AdempiereException(validStatus.getReason());
    }
    final DocumentSaveStatus saveStatus = document.getSaveStatus();
    if (!saveStatus.isSavedOrDeleted()) {
        throw new AdempiereException(saveStatus.getReason());
    }
}


public Builder setProfileId(ViewProfileId profileId){
    this.profileId = profileId;
    return this;
}


public ViewId getParentViewId(){
    return parentViewId;
}


@Override
public long size(){
    return selectionsRef.getSize();
}


public ViewRowIdsOrderedSelection getDefaultSelectionBeforeFacetsFiltering(){
    return selectionsRef.getDefaultSelectionBeforeFacetsFiltering();
}


@Override
public IViewRow getById(DocumentId rowId){
    assertNotClosed();
    return getOrRetrieveById(rowId);
}


public Builder setViewId(ViewId viewId){
    this.viewId = viewId;
    return this;
}


public boolean isApplySecurityRestrictions(){
    return applySecurityRestrictions;
}


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    assertNotClosed();
    return viewFilterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
    final Set<DocumentId> rowIds = viewInvalidationAdvisor.findAffectedRowIds(recordRefs, this);
    if (rowIds.isEmpty()) {
        return;
    }
    // 
    // Schedule rows to be checked and added or removed from current view
    if (refreshViewOnChangeEvents) {
        changedRowIdsToCheck.addChangedRows(rowIds);
    }
    // Invalidate local rowsById cache
    cache_rowsById.removeAll(rowIds);
    // Collect event
    // TODO: check which rowIds are contained in this view and fire events only for those
    ViewChangesCollector.getCurrentOrAutoflush().collectRowsChanged(this, rowIds);
}


@Override
public String getTableNameOrNull(DocumentId ignored){
    return viewDataRepository.getTableName();
}


public ViewRowIdsOrderedSelection getOrderedSelection(DocumentQueryOrderByList orderBys){
    return selectionsRef.getOrderedSelection(orderBys);
}


public DocumentId getParentRowId(){
    return parentRowId;
}


public Builder applySecurityRestrictions(boolean applySecurityRestrictions){
    this.applySecurityRestrictions = applySecurityRestrictions;
    return this;
}


public Builder setParentRowId(DocumentId parentRowId){
    this.parentRowId = parentRowId;
    return this;
}


@Override
public ITranslatableString getDescription(){
    return TranslatableStrings.empty();
}


public List<ViewResultColumn> extractViewResultColumns(List<IViewRow> rows){
    if (rows.isEmpty()) {
        return ImmutableList.of();
    }
    return viewDataRepository.getWidgetTypesByFieldName().entrySet().stream().map(e -> extractViewResultColumnOrNull(e.getKey(), e.getValue(), rows)).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());
}


public JSONViewDataType getViewType(){
    return viewType;
}


public Builder builder(IViewDataRepository viewDataRepository){
    return new Builder(viewDataRepository);
}


public Builder setViewType(JSONViewDataType viewType){
    this.viewType = viewType;
    return this;
}


public DocumentFilterList getStickyFilters(){
    return _stickyFiltersById == null ? DocumentFilterList.EMPTY : DocumentFilterList.ofList(_stickyFiltersById.values());
}


@Override
public void close(ViewCloseAction reason){
    if (closed.getAndSet(true)) {
        // already closed
        return;
    }
    selectionsRef.forgetCurrentSelections();
    logger.debug("View closed with reason={}: {}", reason, this);
}


public IViewRow retrieveRowById(DocumentId rowId){
    final ViewEvaluationCtx evalCtx = getViewEvaluationCtx();
    return viewDataRepository.retrieveById(evalCtx, getViewId(), rowId);
}


@Override
public boolean hasAttributesSupport(){
    return false;
}


public boolean isRefreshViewOnChangeEvents(){
    return refreshViewOnChangeEvents;
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    return viewDataRepository.getSqlWhereClause(getViewId(), getAllFilters(), rowIds, sqlOpts);
}


public void process(Consumer<Set<DocumentId>> consumer){
    if (rowIds.isEmpty()) {
        return;
    }
    consumer.accept(rowIds);
    rowIds.clear();
}


@Override
public int getQueryLimit(){
    return selectionsRef.getQueryLimit();
}


@Override
public ViewResult getPageWithRowIdsOnly(int firstRow,int pageLength,ViewRowsOrderBy orderBy){
    assertNotClosed();
    checkChangedRows();
    final ViewEvaluationCtx evalCtx = getViewEvaluationCtx();
    final ViewRowIdsOrderedSelection orderedSelection = getOrderedSelection(orderBy.toDocumentQueryOrderByList());
    final List<DocumentId> rowIds = viewDataRepository.retrieveRowIdsByPage(evalCtx, orderedSelection, firstRow, pageLength);
    return ViewResult.builder().view(this).firstRow(firstRow).pageLength(pageLength).orderBys(orderedSelection.getOrderBys()).rowIds(rowIds).build();
}


@Override
public boolean isQueryLimitHit(){
    return selectionsRef.isQueryLimitHit();
}


public void assertNotClosed(){
    if (closed.get()) {
        throw new IllegalStateException("View already closed: " + getViewId());
    }
}


public void addChangedRows(Collection<DocumentId> rowIdsToAdd){
    rowIds.addAll(rowIdsToAdd);
}


@Override
public void patchViewRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests){
    final DocumentId rowId = ctx.getRowId();
    final DocumentCollection documentsCollection = ctx.getDocumentsCollection();
    final DocumentPath documentPath = getById(rowId).getDocumentPath();
    Services.get(ITrxManager.class).runInThreadInheritedTrx(() -> documentsCollection.forDocumentWritable(documentPath, NullDocumentChangesCollector.instance, document -> {
        patchDocument(document, fieldChangeRequests);
        return null;
    }));
    invalidateRowById(rowId);
    ViewChangesCollector.getCurrentOrAutoflush().collectRowChanged(this, rowId);
    documentsCollection.invalidateRootDocument(documentPath);
}


public ViewProfileId getProfileId(){
    return profileId;
}


public Builder setParentViewId(ViewId parentViewId){
    this.parentViewId = parentViewId;
    return this;
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return selectionsRef.getDefaultOrderBys();
}


@Override
public void invalidateAll(){
    cache_rowsById.reset();
}


public DefaultView build(){
    return new DefaultView(this);
}


public Builder setFilters(DocumentFilterList filters){
    _filtersById.clear();
    filters.forEach(filter -> _filtersById.put(filter.getFilterId(), filter));
    return this;
}


public IViewInvalidationAdvisor getViewInvalidationAdvisor(){
    return viewInvalidationAdvisor;
}


@Override
public String toString(){
    String toString = _toString;
    if (toString == null) {
        final ViewRowIdsOrderedSelection defaultSelection = selectionsRef.getDefaultSelection();
        // NOTE: keep it short
        toString = _toString = MoreObjects.toStringHelper(this).omitNullValues().add("viewId", defaultSelection.getViewId()).add("tableName", viewDataRepository.getTableName()).add("parentViewId", parentViewId).add("defaultSelection", defaultSelection).toString();
    }
    return toString;
}


@Override
public List<T> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<T> modelClass){
    return viewDataRepository.retrieveModelsByIds(getViewId(), rowIds, modelClass);
}


@NonNull
public ViewId getViewId(){
    return viewId;
}


public void checkChangedRows(){
    if (!refreshViewOnChangeEvents) {
        return;
    }
    changedRowIdsToCheck.process(selectionsRef::removeRowIdsNotMatchingFilters);
}


@Override
public void invalidateSelection(){
    selectionsRef.forgetCurrentSelections();
    invalidateAll();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
}


}