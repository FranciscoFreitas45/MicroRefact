package de.metas.ui.web.order.sales.purchasePlanning.view;
 import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IEditableView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowsOrderBy;
import de.metas.ui.web.view.event.ViewChangesCollector;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class PurchaseView implements IEditableView{

 private  ViewId viewId;

 private  PurchaseRowsCollection rows;

 private  List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    throw new UnsupportedOperationException();
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    if (!orderBys.isEmpty()) {
        throw new AdempiereException("orderBys is not supported");
    }
    final List<PurchaseRow> pageRows = rows.getPage(firstRow, pageLength);
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBys.toDocumentQueryOrderByList(), pageRows);
}


@Override
public String getTableNameOrNull(DocumentId documentId){
    return null;
}


@Override
public LookupValuesList getFieldDropdown(RowEditingContext ctx,String fieldName){
    throw new UnsupportedOperationException();
}


@Override
public DocumentId getParentRowId(){
    return null;
}


public List<PurchaseRow> getRows(){
    return rows.getAll();
}


@Override
public void invalidateRowById(DocumentId rowId){
    ViewChangesCollector.getCurrentOrAutoflush().collectRowChanged(this, rowId);
}


@Override
public LookupValuesList getFieldTypeahead(RowEditingContext ctx,String fieldName,String query){
    throw new UnsupportedOperationException();
}


public PurchaseView cast(Object view){
    return (PurchaseView) view;
}


@Override
public JSONViewDataType getViewType(){
    return JSONViewDataType.grid;
}


@Override
public Set<DocumentPath> getReferencingDocumentPaths(){
    return ImmutableSet.of();
}


@Override
public DocumentFilterList getFilters(){
    return DocumentFilterList.EMPTY;
}


@Override
public DocumentFilterList getStickyFilters(){
    return DocumentFilterList.EMPTY;
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    throw new UnsupportedOperationException();
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
    return rows.streamTopLevelRowsByIds(rowIds);
}


public void patchViewRow(PurchaseRowId idOfChangedRow,PurchaseRowChangeRequest rowChangeRequest){
    rows.patchRow(idOfChangedRow, rowChangeRequest);
    // notify the frontend
    final DocumentId groupRowDocumentId = idOfChangedRow.toGroupRowId().toDocumentId();
    ViewChangesCollector.getCurrentOrAutoflush().collectRowChanged(this, groupRowDocumentId);
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
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
}


public PurchaseRow getById(PurchaseRowId rowId){
    return rows.getById(rowId);
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