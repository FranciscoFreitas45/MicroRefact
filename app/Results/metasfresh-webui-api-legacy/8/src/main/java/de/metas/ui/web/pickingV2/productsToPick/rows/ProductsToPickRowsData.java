package de.metas.ui.web.pickingV2.productsToPick.rows;
 import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.model.I_M_Picking_Candidate;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateId;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.template.IEditableRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
@ToString(of = { "_allRowsById", "orderBys" })
public class ProductsToPickRowsData implements IEditableRowsData<ProductsToPickRow>{

 private  PickingCandidateService pickingCandidateService;

@Getter
 private  DocumentQueryOrderByList orderBys;

 private  ImmutableList<DocumentId> topLevelRowIdsOrdered;

 private  ConcurrentHashMap<DocumentId,ProductsToPickRow> _allRowsById;

 private  boolean rowIdsInvalid;


public Comparator<ProductsToPickRow> createComparator(DocumentQueryOrderByList orderBys){
    return orderBys.toComparator(ProductsToPickRow::getFieldValueAsJsonObject, JSONOptions.newInstance());
}


public void updateInvalidRowsIfNeeded(){
    if (!rowIdsInvalid) {
        return;
    }
    final Map<PickingCandidateId, DocumentId> rowIdsByPickingCandidateId = _allRowsById.values().stream().filter(row -> row.getPickingCandidateId() != null).collect(ImmutableMap.toImmutableMap(ProductsToPickRow::getPickingCandidateId, ProductsToPickRow::getId));
    final List<PickingCandidate> pickingCandidates = pickingCandidateService.getByIds(rowIdsByPickingCandidateId.keySet());
    for (PickingCandidate pickingCandidate : pickingCandidates) {
        _allRowsById.compute(rowIdsByPickingCandidateId.get(pickingCandidate.getId()), (rowId, row) -> row.withUpdatesFromPickingCandidate(pickingCandidate));
    }
    rowIdsInvalid = false;
}


public void updateViewRowFromPickingCandidate(DocumentId rowId,PickingCandidate pickingCandidate){
    changeRow(rowId, row -> row.withUpdatesFromPickingCandidate(pickingCandidate));
}


public Map<DocumentId,ProductsToPickRow> getAllRowsById(){
    updateInvalidRowsIfNeeded();
    return getAllRowsByIdNoUpdate();
}


@Override
public void patchRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests){
    changeRow(ctx.getRowId(), row -> applyFieldChangeRequests(row, fieldChangeRequests));
}


public Map<DocumentId,ProductsToPickRow> getAllRowsByIdNoUpdate(){
    return _allRowsById;
}


@Override
public Collection<ProductsToPickRow> getTopLevelRows(){
    final Map<DocumentId, ProductsToPickRow> rowsById = getAllRowsById();
    return topLevelRowIdsOrdered.stream().map(rowsById::get).collect(ImmutableList.toImmutableList());
}


@Override
public Map<DocumentId,ProductsToPickRow> getDocumentId2TopLevelRows(){
    final Map<DocumentId, ProductsToPickRow> rowsById = getAllRowsById();
    return topLevelRowIdsOrdered.stream().map(rowsById::get).collect(GuavaCollectors.toImmutableMapByKey(ProductsToPickRow::getId));
}


public ProductsToPickRow applyFieldChangeRequests(ProductsToPickRow row,List<JSONDocumentChangedEvent> fieldChangeRequests){
    Check.assumeNotEmpty(fieldChangeRequests, "fieldChangeRequests is not empty");
    fieldChangeRequests.forEach(JSONDocumentChangedEvent::assertReplaceOperation);
    ProductsToPickRow changedRow = row;
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        if (ProductsToPickRow.FIELD_QtyOverride.equals(fieldName)) {
            if (!row.isQtyOverrideEditableByUser()) {
                throw new AdempiereException("QtyOverride is not editable").setParameter("row", row);
            }
            final BigDecimal qtyOverride = fieldChangeRequest.getValueAsBigDecimal();
            changedRow = changedRow.withQtyOverride(qtyOverride);
        } else if (ProductsToPickRow.FIELD_QtyReview.equals(fieldName)) {
            final BigDecimal qtyReviewed = fieldChangeRequest.getValueAsBigDecimal();
            final PickingCandidate pickingCandidate = pickingCandidateService.setQtyReviewed(row.getPickingCandidateId(), qtyReviewed);
            changedRow = changedRow.withUpdatesFromPickingCandidate(pickingCandidate);
        } else {
            throw new AdempiereException("Field " + fieldName + " is not editable");
        }
    }
    return changedRow;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    final Set<PickingCandidateId> pickingCandidateIds = recordRefs.streamIds(I_M_Picking_Candidate.Table_Name, PickingCandidateId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    if (pickingCandidateIds.isEmpty()) {
        return DocumentIdsSelection.EMPTY;
    }
    return getAllRowsByIdNoUpdate().values().stream().filter(row -> pickingCandidateIds.contains(row.getPickingCandidateId())).map(ProductsToPickRow::getId).collect(DocumentIdsSelection.toDocumentIdsSelection());
}


@Override
public Map<DocumentId,ProductsToPickRow> getDocumentId2AllRows(){
    return getAllRowsById();
}


@Override
public int size(){
    return topLevelRowIdsOrdered.size();
}


@Override
public void invalidateAll(){
    rowIdsInvalid = true;
}


public void changeRow(DocumentId rowId,UnaryOperator<ProductsToPickRow> mapper){
    final Map<DocumentId, ProductsToPickRow> rowsById = getAllRowsById();
    rowsById.compute(rowId, (k, row) -> {
        if (row == null) {
            throw new AdempiereException("No row found for id: " + k);
        } else {
            final ProductsToPickRow newRow = mapper.apply(row);
            Check.assumeNotNull(newRow, "newRow shall not be null");
            return newRow;
        }
    });
}


}