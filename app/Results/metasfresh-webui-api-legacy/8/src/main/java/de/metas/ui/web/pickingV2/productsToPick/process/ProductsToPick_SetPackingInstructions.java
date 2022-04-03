package de.metas.ui.web.pickingV2.productsToPick.process;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableMap;
import de.metas.handlingunits.HuPackingInstructionsId;
import de.metas.handlingunits.picking.PickingCandidate;
import de.metas.handlingunits.picking.PickingCandidateId;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.pickingV2.productsToPick.rows.ProductsToPickRow;
import de.metas.ui.web.window.datatypes.DocumentId;
public class ProductsToPick_SetPackingInstructions extends ProductsToPickViewBasedProcess{

@Autowired
 private  PickingCandidateService pickingCandidateService;

@Param(parameterName = "M_HU_PI_ID", mandatory = true)
 private  int p_M_HU_PI_ID;


public Stream<ProductsToPickRow> streamRowsEligibleForPacking(){
    return getSelectedRows().stream().filter(ProductsToPickRow::isEligibleForPacking);
}


@Override
@RunOutOfTrx
public String doIt(){
    final Map<PickingCandidateId, DocumentId> rowIdsByPickingCandidateId = streamRowsEligibleForPacking().collect(ImmutableMap.toImmutableMap(ProductsToPickRow::getPickingCandidateId, ProductsToPickRow::getId));
    final Set<PickingCandidateId> pickingCandidateIds = rowIdsByPickingCandidateId.keySet();
    final HuPackingInstructionsId huPackingInstructionsId = getHuPackingInstructionsId();
    final List<PickingCandidate> pickingCandidates = pickingCandidateService.setHuPackingInstructionId(pickingCandidateIds, huPackingInstructionsId);
    pickingCandidates.forEach(pickingCandidate -> {
        final DocumentId rowId = rowIdsByPickingCandidateId.get(pickingCandidate.getId());
        updateViewRowFromPickingCandidate(rowId, pickingCandidate);
    });
    invalidateView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isPickerProfile()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("only picker shall pack");
    }
    if (!streamRowsEligibleForPacking().findAny().isPresent()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("no eligible rows were selected");
    }
    return ProcessPreconditionsResolution.accept();
}


public HuPackingInstructionsId getHuPackingInstructionsId(){
    return HuPackingInstructionsId.ofRepoId(p_M_HU_PI_ID);
}


}