package de.metas.ui.web.order.pricingconditions.view;
 import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.money.CurrencyId;
import de.metas.order.OrderLineId;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.template.IEditableRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@ToString(doNotUseGetters = true)
public class PricingConditionsRowData implements IEditableRowsData<PricingConditionsRow>{

@Getter
 private  OrderLineId orderLineId;

 private  DocumentFilterList filters;

 private  PricingConditionsRowData allRowsData;

 private  ImmutableList<DocumentId> rowIds;

 private  ConcurrentMap<DocumentId,PricingConditionsRow> rowsById;

 private  DocumentId editableRowId;

@Getter
 private  CurrencyId defaultCurrencyId;


public DocumentId getEditableRowId(){
    if (editableRowId == null) {
        throw new AdempiereException("No editable row found");
    }
    return editableRowId;
}


@Override
public void patchRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests){
    final PricingConditionsRowChangeRequest request = PricingConditionsRowActions.toChangeRequest(fieldChangeRequests, getDefaultCurrencyId());
    changeRow(ctx.getRowId(), row -> PricingConditionsRowReducers.copyAndChange(request, row));
}


@Override
public Collection<PricingConditionsRow> getAllRows(){
    return getTopLevelRows();
}


@Override
public Collection<PricingConditionsRow> getTopLevelRows(){
    return rowIds.stream().map(rowsById::get).collect(ImmutableList.toImmutableList());
}


@Override
public Map<DocumentId,PricingConditionsRow> getDocumentId2TopLevelRows(){
    return ImmutableMap.copyOf(rowsById);
}


public boolean hasEditableRow(){
    return editableRowId != null;
}


public PricingConditionsRowData filter(DocumentFilterList filters){
    if (DocumentFilterList.equals(this.filters, filters)) {
        return this;
    }
    if (filters.isEmpty()) {
        return getAllRowsData();
    }
    return new PricingConditionsRowData(this, filters);
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return DocumentIdsSelection.EMPTY;
}


@Override
public Map<DocumentId,PricingConditionsRow> getDocumentId2AllRows(){
    return getDocumentId2TopLevelRows();
}


public PricingConditionsRow getEditableRow(){
    return getById(getEditableRowId());
}


@Override
public int size(){
    return rowIds.size();
}


@Override
public void invalidateAll(){
}


public Stream<PricingConditionsRow> stream(PricingConditionsRow editableRow,List<PricingConditionsRow> rows){
    return editableRow != null ? Stream.concat(Stream.of(editableRow), rows.stream()) : rows.stream();
}


public void changeRow(DocumentId rowId,UnaryOperator<PricingConditionsRow> mapper){
    if (!rowIds.contains(rowId)) {
        throw new EntityNotFoundException(rowId.toJson());
    }
    rowsById.compute(rowId, (key, oldRow) -> {
        if (oldRow == null) {
            throw new EntityNotFoundException(rowId.toJson());
        }
        return mapper.apply(oldRow);
    });
}


public DocumentFilterList getFilters(){
    return filters;
}


public PricingConditionsRowData getAllRowsData(){
    return allRowsData != null ? allRowsData : this;
}


public void patchEditableRow(PricingConditionsRowChangeRequest request){
    changeRow(getEditableRowId(), editableRow -> PricingConditionsRowReducers.copyAndChange(request, editableRow));
}


}