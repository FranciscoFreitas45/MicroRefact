package de.metas.ui.web.pickingV2.productsToPick.process;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.picking.candidate.commands.RejectPickingResult;
import de.metas.handlingunits.picking.requests.RejectPickingRequest;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
public class ProductsToPick_MarkWillNotPickSelected extends ProductsToPickViewBasedProcess{

@Autowired
 private  PickingCandidateService pickingCandidatesService;


public void markAsWillNotPick(ProductsToPickRow row){
    final RejectPickingResult result = pickingCandidatesService.rejectPicking(RejectPickingRequest.builder().shipmentScheduleId(row.getShipmentScheduleId()).qtyToReject(row.getQtyEffective()).rejectPickingFromHuId(row.getPickFromHUId()).existingPickingCandidateId(row.getPickingCandidateId()).build());
    updateViewRowFromPickingCandidate(row.getId(), result.getPickingCandidate());
}


@Override
public String doIt(){
    getSelectedRows().stream().filter(ProductsToPickRow::isEligibleForRejectPicking).forEach(this::markAsWillNotPick);
    invalidateView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final List<ProductsToPickRow> selectedRows = getSelectedRows();
    if (selectedRows.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection().toInternal();
    }
    if (!selectedRows.stream().allMatch(ProductsToPickRow::isEligibleForRejectPicking)) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select only rows that can be picked");
    }
    return ProcessPreconditionsResolution.accept();
}


}