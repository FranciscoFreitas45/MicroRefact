package de.metas.ui.web.handlingunits;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.GuavaCollectors;
import de.metas.util.Services;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class HUEditorView implements IView{

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  ViewId viewId;

 private  JSONViewDataType viewType;

 private  String referencingTableName;

 private  Set<DocumentPath> referencingDocumentPaths;

 private  ViewActionDescriptorsList actions;

 private  ImmutableList<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  HUEditorViewBuffer rowsBuffer;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  DocumentFilterList filters;

 private  ImmutableMap<String,Object> parameters;


public void addHUIdsAndInvalidate(Collection<HuId> huIdsToAdd){
    if (addHUIds(huIdsToAdd)) {
        invalidateAll();
    }
}


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    return filterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
    // TODO: notifyRecordsChanged:
    // get M_HU_IDs from recordRefs,
    // find the top level records from this view which contain our HUs
    // invalidate those top levels only
    final Set<HuId> huIdsToCheck = recordRefs.streamIds(I_M_HU.Table_Name, HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    if (huIdsToCheck.isEmpty()) {
        return;
    }
    final boolean containsSomeRecords = rowsBuffer.containsAnyOfHUIds(huIdsToCheck);
    if (!containsSomeRecords) {
        return;
    }
    invalidateAll();
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final List<HUEditorRow> page = rowsBuffer.streamPage(firstRow, pageLength, HUEditorRowFilter.ALL, orderBys).collect(GuavaCollectors.toImmutableList());
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), page);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    // commented out for now, see javadoc
    // if (documentId == null)
    // {
    // return null;
    // }
    // final HUEditorRow huEditorRow = getById(documentId);
    // final HUEditorRowType type = huEditorRow.getType();
    // if (type == HUEditorRowType.HUStorage)
    // {
    // return I_M_HU_Storage.Table_Name;
    // }
    return I_M_HU.Table_Name;
}


public String getReferencingTableName(){
    return referencingTableName;
}


public boolean removeHUIds(Collection<HuId> huIdsToRemove){
    return rowsBuffer.removeHUIds(huIdsToRemove);
}


@Override
public DocumentId getParentRowId(){
    return parentRowId;
}


@Override
public ViewActionDescriptorsList getActions(){
    return actions;
}


@Override
public ITranslatableString getDescription(){
    return TranslatableStrings.empty();
}


public void removeHUIdsAndInvalidate(Collection<HuId> huIdsToRemove){
    if (removeHUIds(huIdsToRemove)) {
        invalidateAll();
    }
}


public HUEditorView cast(IView view){
    return (HUEditorView) view;
}


public boolean matchesAnyRowRecursive(HUEditorRowFilter filter){
    return rowsBuffer.matchesAnyRowRecursive(filter);
}


@Override
public JSONViewDataType getViewType(){
    return viewType;
}


public void addHUsAndInvalidate(Collection<I_M_HU> husToAdd){
    addHUIdsAndInvalidate(extractHUIds(husToAdd));
}


public void removeHUsAndInvalidate(Collection<I_M_HU> husToRemove){
    final Set<HuId> huIdsToRemove = extractHUIds(husToRemove);
    removeHUIdsAndInvalidate(huIdsToRemove);
}


@Override
public Set<DocumentPath> getReferencingDocumentPaths(){
    return referencingDocumentPaths;
}


public HUEditorViewBuilder builder(){
    return new HUEditorViewBuilder();
}


@Override
public DocumentFilterList getFilters(){
    return filters;
}


@Override
public DocumentFilterList getStickyFilters(){
    return rowsBuffer.getStickyFilters();
}


public void addHUIdAndInvalidate(HuId huId){
    addHUIdsAndInvalidate(ImmutableSet.of(huId));
}


@Override
public boolean hasAttributesSupport(){
    return true;
}


public void invalidateAllNoNotify(){
    rowsBuffer.invalidateAll();
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts_NOTUSED){
    return rowsBuffer.getSqlWhereClause(rowIds);
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    return filterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx, query);
}


@Override
public int getQueryLimit(){
    return -1;
}


@Override
public boolean isQueryLimitHit(){
    return false;
}


@Override
public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors(){
    return additionalRelatedProcessDescriptors;
}


@Override
public TableRecordReference getTableRecordReferenceOrNull(DocumentId rowId){
    if (rowId == null) {
        return null;
    }
    final HUEditorRowId huRowId = HUEditorRowId.ofDocumentId(rowId);
    if (huRowId.isHU()) {
        return TableRecordReference.of(I_M_HU.Table_Name, huRowId.getHuId());
    } else {
        return null;
    }
}


public HUEditorRow getParentRowByChildIdOrNull(DocumentId childId){
    return rowsBuffer.getParentRowByChildIdOrNull(childId).orElse(null);
}


public Stream<HUEditorRow> streamByIds(HUEditorRowFilter filter){
    return rowsBuffer.streamByIdsExcludingIncludedRows(filter);
}


public Stream<HUEditorRow> streamAllRecursive(HUEditorRowFilter filter){
    return rowsBuffer.streamAllRecursive(filter);
}


public boolean getParameterAsBoolean(String name,boolean defaultValue){
    final Boolean value = (Boolean) parameters.get(name);
    return value != null ? value.booleanValue() : defaultValue;
}


public boolean addHUId(HuId huIdToAdd){
    return rowsBuffer.addHUIds(ImmutableSet.of(huIdToAdd));
}


public boolean addHUIds(Collection<HuId> huIdsToAdd){
    return rowsBuffer.addHUIds(huIdsToAdd);
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return DocumentQueryOrderByList.EMPTY;
}


@Override
public ViewId getParentViewId(){
    return parentViewId;
}


@Override
public long size(){
    return rowsBuffer.size();
}


@Override
public void invalidateAll(){
    invalidateAllNoNotify();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
}


@Override
public HUEditorRow getById(DocumentId rowId){
    return rowsBuffer.getById(rowId);
}


@Override
public void afterDestroy(){
    invalidateAllNoNotify();
}


public ImmutableMap<String,Object> getParameters(){
    return parameters;
}


public Optional<T> getParameterAsId(String name){
    @SuppressWarnings("unchecked")
    final T value = (T) parameters.get(name);
    return Optional.ofNullable(value);
}


@Override
public List<T> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<T> modelClass){
    final Set<HuId> huIds = streamByIds(rowIds).filter(HUEditorRow::isPureHU).map(HUEditorRow::getHuId).filter(Objects::nonNull).collect(ImmutableSet.toImmutableSet());
    if (huIds.isEmpty()) {
        return ImmutableList.of();
    }
    final List<I_M_HU> hus = Services.get(IQueryBL.class).createQueryBuilder(I_M_HU.class).addInArrayFilter(I_M_HU.COLUMN_M_HU_ID, huIds).create().list(I_M_HU.class);
    return InterfaceWrapperHelper.createList(hus, modelClass);
}


@Override
public ViewId getViewId(){
    return viewId;
}


public Set<HuId> extractHUIds(Collection<I_M_HU> hus){
    if (hus == null || hus.isEmpty()) {
        return ImmutableSet.of();
    }
    return hus.stream().filter(Objects::nonNull).map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(Collectors.toSet());
}


}