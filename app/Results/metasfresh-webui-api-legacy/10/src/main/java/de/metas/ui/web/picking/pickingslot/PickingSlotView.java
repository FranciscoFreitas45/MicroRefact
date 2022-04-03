package de.metas.ui.web.picking.pickingslot;
 import java.util.List;
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
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.picking.model.I_M_PickingSlot;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.picking.packageable.PackageableView;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.Builder;
import lombok.NonNull;
public class PickingSlotView implements IView{

 private  ViewId viewId;

 private  ViewId parentViewId;

 private  DocumentId parentRowId;

 private  ITranslatableString description;

 private  ShipmentScheduleId currentShipmentScheduleId;

 private  PickingSlotRowsCollection rows;

 private  ImmutableList<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

 private  DocumentFilterList filters;


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    throw new UnsupportedOperationException();
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
// TODO Auto-generated method stub
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final List<PickingSlotRow> pageRows = rows.getPage(firstRow, pageLength);
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), pageRows);
}


@Override
public String getTableNameOrNull(DocumentId ignored){
    return I_M_PickingSlot.Table_Name;
}


@Override
public DocumentId getParentRowId(){
    return parentRowId;
}


@Override
public ITranslatableString getDescription(){
    return description;
}


public PickingSlotView cast(IView pickingSlotView){
    return (PickingSlotView) pickingSlotView;
}


@Override
public JSONViewDataType getViewType(){
    return JSONViewDataType.grid;
}


@Override
public Set<DocumentPath> getReferencingDocumentPaths(){
    return ImmutableSet.of();
}


@NonNull
public ShipmentScheduleId getCurrentShipmentScheduleId(){
    return currentShipmentScheduleId;
}


@Override
public DocumentFilterList getFilters(){
    return filters;
}


@Override
public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.EMPTY;
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    // TODO Auto-generated method stub
    return null;
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    throw new UnsupportedOperationException();
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
public Stream<? extends IViewRow> streamByIds(DocumentIdsSelection rowIds){
    return rows.streamByIds(rowIds);
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
    return rows.size();
}


@Override
public void invalidateAll(){
    rows.invalidateAll();
}


@Override
public PickingSlotRow getById(DocumentId id){
    return rows.getById(id);
}


@Override
public List<T> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<T> modelClass){
    throw new UnsupportedOperationException();
}


@Override
public ViewId getViewId(){
    return viewId;
}


}