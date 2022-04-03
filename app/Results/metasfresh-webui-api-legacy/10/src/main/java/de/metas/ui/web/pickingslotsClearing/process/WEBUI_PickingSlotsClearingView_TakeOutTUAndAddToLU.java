package de.metas.ui.web.pickingslotsClearing.process;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.model.InterfaceWrapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.storage.EmptyHUListener;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.util.Check;
public class WEBUI_PickingSlotsClearingView_TakeOutTUAndAddToLU extends PickingSlotsClearingViewBasedProcessimplements IProcessPrecondition{

@Autowired
 private  PickingCandidateService pickingCandidateService;


@Override
public String doIt(){
    final PickingSlotRow pickingSlotRow = getSingleSelectedPickingSlotRow();
    Check.assume(pickingSlotRow.isTU(), "Picking slot HU shall be a TU: {}", pickingSlotRow);
    final I_M_HU tuHU = InterfaceWrapperHelper.load(pickingSlotRow.getHuId(), I_M_HU.class);
    final HUEditorRow packingHURow = getSingleSelectedPackingHUsRow();
    Check.assume(packingHURow.isLU(), "Pack to HU shall be a LU: {}", packingHURow);
    final I_M_HU luHU = packingHURow.getM_HU();
    final BigDecimal qtyTU = BigDecimal.ONE;
    final List<Integer> huIdsDestroyedCollector = new ArrayList<>();
    HUTransformService.builder().emptyHUListener(EmptyHUListener.doBeforeDestroyed(hu -> huIdsDestroyedCollector.add(hu.getM_HU_ID()))).build().tuToExistingLU(tuHU, qtyTU, luHU);
    // Remove from picking slots all destroyed HUs
    pickingCandidateService.inactivateForHUIds(HuId.fromRepoIds(huIdsDestroyedCollector));
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    // 
    // Validate the picking slots clearing selected row (left side)
    if (!isSingleSelectedPickingSlotRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select one and only one picking slots HU");
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedPickingSlotRow();
    if (!pickingSlotRow.isTU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a TU");
    }
    // 
    // Validate the packing HUs selected row (right side)
    if (!isSingleSelectedPackingHUsRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select one and only one HU to pack to");
    }
    final HUEditorRow packingHURow = getSingleSelectedPackingHUsRow();
    if (!packingHURow.isLU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a LU to pack too");
    }
    // 
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    // Invalidate views
    getPickingSlotsClearingView().invalidateAll();
    getPackingHUsView().invalidateAll();
}


}