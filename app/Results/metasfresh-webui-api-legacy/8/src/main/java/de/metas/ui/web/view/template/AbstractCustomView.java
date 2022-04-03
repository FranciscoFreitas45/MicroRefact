package de.metas.ui.web.view.template;
 import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import org.compiere.util.Evaluatee;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.view.ViewRowIdsSelection;
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
import de.metas.util.Check;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
public class AbstractCustomView implements IView{

@Getter
 private  ViewId viewId;

@Getter
 private  ITranslatableString description;

@Getter(AccessLevel.PROTECTED)
 private  IRowsData<T> rowsData;

 private  DocumentFilterDescriptorsProvider viewFilterDescriptors;


@Override
public LookupValuesList getFilterParameterDropdown(String filterId,String filterParameterName,Evaluatee ctx){
    return viewFilterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx);
}


@Override
public void notifyRecordsChanged(TableRecordReferenceSet recordRefs){
    if (recordRefs.isEmpty()) {
        // nothing to do, but shall not happen
        return;
    }
    final TableRecordReferenceSet recordRefsEligible = recordRefs.filter(this::isEligibleInvalidateEvent);
    if (recordRefsEligible.isEmpty()) {
        // nothing to do
        return;
    }
    final DocumentIdsSelection documentIdsToInvalidate = getDocumentIdsToInvalidate(recordRefsEligible);
    if (documentIdsToInvalidate.isEmpty()) {
        // nothing to do
        return;
    }
    rowsData.invalidate(documentIdsToInvalidate);
    ViewChangesCollector.getCurrentOrAutoflush().collectRowsChanged(this, documentIdsToInvalidate);
}


@Override
public ViewResult getPage(int firstRow,int pageLength,ViewRowsOrderBy orderBys){
    final ViewRowsOrderBy orderBysEffective = !orderBys.isEmpty() ? orderBys : orderBys.withOrderBys(getDefaultOrderBys());
    final List<IViewRow> pageRows = getRows().stream().sorted(orderBysEffective.toComparator()).skip(firstRow >= 0 ? firstRow : 0).limit(pageLength > 0 ? pageLength : 30).collect(ImmutableList.toImmutableList());
    return ViewResult.ofViewAndPage(this, firstRow, pageLength, orderBysEffective.toDocumentQueryOrderByList(), pageRows);
}


@Override
public DocumentId getParentRowId(){
    return null;
}


public Collection<T> getRows(){
    return rowsData.getTopLevelRows();
}


public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return rowsData.getDocumentIdsToInvalidate(recordRefs);
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
public boolean hasAttributesSupport(){
    return false;
}


@Override
public String getSqlWhereClause(DocumentIdsSelection rowIds,SqlOptions sqlOpts){
    return null;
}


@Override
public LookupValuesList getFilterParameterTypeahead(String filterId,String filterParameterName,String query,Evaluatee ctx){
    return viewFilterDescriptors.getByFilterId(filterId).getParameterByName(filterParameterName).getLookupDataSource().get().findEntities(ctx, query);
}


@Override
public int getQueryLimit(){
    return -1;
}


@Override
public boolean isQueryLimitHit(){
    return false;
}


public DocumentFilterDescriptorsProvider getFilterDescriptors(){
    return viewFilterDescriptors;
}


public Stream<T> streamByIds(ViewRowIdsSelection selection){
    if (!Objects.equals(getViewId(), selection.getViewId())) {
        throw new AdempiereException("Selection has invalid viewId: " + selection + "\nExpected viewId: " + getViewId());
    }
    return streamByIds(selection.getRowIds());
}


public void patchViewRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests){
    Check.assumeNotEmpty(fieldChangeRequests, "fieldChangeRequests is not empty");
    if (rowsData instanceof IEditableRowsData) {
        final IEditableRowsData<T> editableRowsData = (IEditableRowsData<T>) rowsData;
        editableRowsData.patchRow(ctx, fieldChangeRequests);
    } else {
        throw new AdempiereException("View is not editable");
    }
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
    return rowsData.size();
}


@Override
public void invalidateAll(){
    rowsData.invalidateAll();
    ViewChangesCollector.getCurrentOrAutoflush().collectFullyChanged(this);
}


@Override
public T getById(DocumentId rowId){
    return rowsData.getById(rowId);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("viewId", getViewId()).toString();
}


@Override
public List<MT> retrieveModelsByIds(DocumentIdsSelection rowIds,Class<MT> modelClass){
    throw new UnsupportedOperationException();
}


public boolean isEligibleInvalidateEvent(TableRecordReference recordRef){
    return true;
}


}