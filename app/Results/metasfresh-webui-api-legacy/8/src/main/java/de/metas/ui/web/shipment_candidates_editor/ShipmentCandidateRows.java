package de.metas.ui.web.shipment_candidates_editor;
 import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;
import org.adempiere.util.lang.impl.TableRecordReferenceSet;
import java.util.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.metas.inoutcandidate.api.ShipmentScheduleUserChangeRequest;
import de.metas.inoutcandidate.api.ShipmentScheduleUserChangeRequestsList;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.shipment_candidates_editor.ShipmentCandidateRowUserChangeRequest.ShipmentCandidateRowUserChangeRequestBuilder;
import de.metas.ui.web.view.IEditableView.RowEditingContext;
import de.metas.ui.web.view.template.IEditableRowsData;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.Builder;
import lombok.NonNull;
public class ShipmentCandidateRows implements IEditableRowsData<ShipmentCandidateRow>{

 private  ImmutableList<DocumentId> rowIds;

 private  ConcurrentHashMap<DocumentId,ShipmentCandidateRow> rowsById;


public ShipmentCandidateRows cast(IRowsData<ShipmentCandidateRow> rowsData){
    return (ShipmentCandidateRows) rowsData;
}


@Override
public DocumentIdsSelection getDocumentIdsToInvalidate(TableRecordReferenceSet recordRefs){
    return DocumentIdsSelection.EMPTY;
}


public ShipmentCandidateRowUserChangeRequest toUserChangeRequest(List<JSONDocumentChangedEvent> fieldChangeRequests){
    Check.assumeNotEmpty(fieldChangeRequests, "fieldChangeRequests is not empty");
    final ShipmentCandidateRowUserChangeRequestBuilder builder = ShipmentCandidateRowUserChangeRequest.builder();
    for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests) {
        final String fieldName = fieldChangeRequest.getPath();
        if (ShipmentCandidateRow.FIELD_qtyToDeliverUserEntered.equals(fieldName)) {
            builder.qtyToDeliverUserEntered(fieldChangeRequest.getValueAsBigDecimal());
        } else if (ShipmentCandidateRow.FIELD_qtyToDeliverCatchOverride.equals(fieldName)) {
            builder.qtyToDeliverCatchOverride(fieldChangeRequest.getValueAsBigDecimal());
        } else if (ShipmentCandidateRow.FIELD_asi.equals(fieldName)) {
            builder.asi(fieldChangeRequest.getValueAsIntegerLookupValue());
        }
    }
    return builder.build();
}


@Override
public void invalidateAll(){
// nothing
}


public Optional<ShipmentScheduleUserChangeRequestsList> createShipmentScheduleUserChangeRequestsList(){
    final ImmutableList<ShipmentScheduleUserChangeRequest> userChanges = rowsById.values().stream().map(row -> row.createShipmentScheduleUserChangeRequest().orElse(null)).filter(Objects::nonNull).collect(ImmutableList.toImmutableList());
    return !userChanges.isEmpty() ? Optional.of(ShipmentScheduleUserChangeRequestsList.of(userChanges)) : Optional.empty();
}


@Override
public void patchRow(RowEditingContext ctx,List<JSONDocumentChangedEvent> fieldChangeRequests){
    final ShipmentCandidateRowUserChangeRequest userChanges = toUserChangeRequest(fieldChangeRequests);
    changeRow(ctx.getRowId(), row -> row.withChanges(userChanges));
}


public void changeRow(DocumentId rowId,UnaryOperator<ShipmentCandidateRow> mapper){
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


@Override
public Map<DocumentId,ShipmentCandidateRow> getDocumentId2TopLevelRows(){
    return rowIds.stream().map(rowsById::get).collect(GuavaCollectors.toImmutableMapByKey(ShipmentCandidateRow::getId));
}


}