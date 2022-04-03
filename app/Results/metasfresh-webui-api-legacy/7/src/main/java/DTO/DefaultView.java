package DTO;
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

 private  IViewDataRepository viewDataRepository;

 private  ViewId viewId;

 private  AtomicBoolean closed;

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  JSONViewDataType viewType;

 private  ViewProfileId profileId;

 private  ImmutableSet<DocumentPath> referencingDocumentPaths;

 private  ViewEvaluationCtx viewEvaluationCtx;

 private  ViewRowIdsOrderedSelectionsHolder selectionsRef;

 private  DocumentFilterDescriptorsProvider viewFilterDescriptors;

 private  DocumentFilterList stickyFilters;

 private  DocumentFilterList filters;

 private  DocumentFilterList _allFilters;

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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


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


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    return viewDataRepository.getViewFilterDescriptors();
}


public DocumentFilterDescriptorsProvider getViewFilterDescriptors(){
    return viewDataRepository.getViewFilterDescriptors();
}


public DocumentFilterList getAllFilters(){
    DocumentFilterList allFilters = _allFilters;
    if (allFilters == null) {
        _allFilters = allFilters = getFilters().mergeWith(getStickyFilters());
    }
    return allFilters;
}


public ViewId getParentViewId(){
    return parentViewId;
}


public ViewRowIdsOrderedSelection getDefaultSelectionBeforeFacetsFiltering(){
    return selectionsRef.getDefaultSelectionBeforeFacetsFiltering();
}


@Override
public IViewRow getById(DocumentId rowId){
    assertNotClosed();
    return getOrRetrieveById(rowId);
}


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    assertNotClosed();
    return viewFilterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
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


@Override
public ITranslatableString getDescription(){
    return TranslatableStrings.empty();
}


public JSONViewDataType getViewType(){
    return viewType;
}


public DocumentFilterList getStickyFilters(){
    return _stickyFiltersById == null ? DocumentFilterList.EMPTY : DocumentFilterList.ofList(_stickyFiltersById.values());
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    return viewDataRepository.getSqlWhereClause(getViewId(), getAllFilters(), rowIds, sqlOpts);
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


public ViewProfileId getProfileId(){
    return profileId;
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return selectionsRef.getDefaultOrderBys();
}


public IViewInvalidationAdvisor getViewInvalidationAdvisor(){
    return viewInvalidationAdvisor;
}


@NonNull
public ViewId getViewId(){
    return viewId;
}


public DefaultView cast(IView view){
    return (DefaultView) view;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("view",view);
DefaultView aux = restTemplate.getForObject(builder.toUriString(),DefaultView.class);
return aux;
}


}