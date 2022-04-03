package de.metas.ui.web.pickingslotsClearing;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.picking.model.I_M_PickingSlot;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowsCollection;
import de.metas.ui.web.pickingslotsClearing.PackingHUsViewsCollection.PackingHUsViewSupplier;
import de.metas.ui.web.pickingslotsClearing.process.HUExtractedFromPickingSlotEvent;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.ViewCloseAction;
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
import lombok.Builder;
import lombok.NonNull;
public class PickingSlotsClearingView implements IViewRowOverrides,IView{

 private  ViewId viewId;

 private  ITranslatableString description;

 private  ImmutableList<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  PickingSlotRowsCollection rows;

 private  PackingHUsViewsCollection packingHUsViewsCollection;

 private  DocumentFilterDescriptorsProvider filterDescriptors;

 private  DocumentFilterList filters;


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    return filterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
// TODO Auto-generated method stub
}


public void handleEvent(HUExtractedFromPickingSlotEvent event){
    packingHUsViewsCollection.handleEvent(event);
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final List<PickingSlotRow> pageRows = rows.getPage(firstRow, pageLength);
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), pageRows);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return I_M_PickingSlot.Table_Name;
}


public PackingHUsViewKey extractPackingHUsViewKey(PickingSlotRow row){
    final PickingSlotRow rootRow = getRootRowWhichIncludesRowId(row.getPickingSlotRowId());
    return PackingHUsViewKey.builder().pickingSlotsClearingViewIdPart(getViewId().getViewIdPart()).bpartnerId(rootRow.getBPartnerId()).bpartnerLocationId(rootRow.getBPartnerLocationId()).build();
}


@Override
public DocumentId getParentRowId(){
    return null;
}


public void closePackingHUsView(ViewId packingHUsViewId,ViewCloseAction closeAction){
    final PackingHUsViewKey key = PackingHUsViewKey.ofPackingHUsViewId(packingHUsViewId);
    packingHUsViewsCollection.removeIfExists(key).ifPresent(packingHUsView -> packingHUsView.close(closeAction));
}


@Override
public ITranslatableString getDescription(){
    return description;
}


@Override
public JSONViewDataType getViewType(){
    return JSONViewDataType.grid;
}


public PickingSlotRowId getRootRowIdWhichIncludesRowId(PickingSlotRowId rowId){
    return rows.getRootRowIdWhichIncludes(rowId);
}


public HUEditorView computePackingHUsViewIfAbsent(ViewId packingHUsViewId,PackingHUsViewSupplier packingHUsViewFactory){
    return packingHUsViewsCollection.computeIfAbsent(PackingHUsViewKey.ofPackingHUsViewId(packingHUsViewId), packingHUsViewFactory::createPackingHUsView);
}


@Override
public Set<DocumentPath> getReferencingDocumentPaths(){
    return ImmutableSet.of();
}


@Override
public DocumentFilterList getFilters(){
    return filters;
}


@Override
public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.EMPTY;
}


public PickingSlotRow getRootRowWhichIncludesRowId(PickingSlotRowId rowId){
    return rows.getRootRowWhichIncludes(rowId);
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    // TODO Auto-generated method stub
    return null;
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
public ViewId getIncludedViewId(IViewRow row){
    final PackingHUsViewKey key = extractPackingHUsViewKey(PickingSlotRow.cast(row));
    return key.getPackingHUsViewId();
}


@Override
public Stream<PickingSlotRow> streamByIds(DocumentIdsSelection rowIds){
    return rows.streamByIds(rowIds);
}


@Override
public DocumentQueryOrderByList getDefaultOrderBys(){
    return DocumentQueryOrderByList.EMPTY;
}


@Override
public ViewId getParentViewId(){
    return null;
}


@Override
public long size(){
    return rows.size();
}


@Override
public void invalidateAll(){
    rows.invalidateAll();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
}


public PickingSlotRow getById(PickingSlotRowId rowId){
    return getById(rowId.toDocumentId());
}


@Override
public List<T> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<T> modelClass){
    return ImmutableList.of();
}


public Optional<HUEditorView> getPackingHUsViewIfExists(PickingSlotRowId rowId){
    final PickingSlotRow rootRow = getRootRowWhichIncludesRowId(rowId);
    final PackingHUsViewKey key = extractPackingHUsViewKey(rootRow);
    return packingHUsViewsCollection.getByKeyIfExists(key);
}


@Override
public ViewId getViewId(){
    return viewId;
}


}