package de.metas.ui.web.pickingslotsClearing.process;
 import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.allocation.IAllocationSource;
import de.metas.handlingunits.allocation.IHUProducerAllocationDestination;
import de.metas.handlingunits.allocation.impl.HUListAllocationSourceDestination;
import de.metas.handlingunits.allocation.impl.HULoader;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotRowId;
import de.metas.util.Services;
public class WEBUI_PickingSlotsClearingView_TakeOutMultiHUsAndAddToNewHU extends PickingSlotsClearingViewBasedProcessimplements IProcessPrecondition{

 private  IHandlingUnitsBL handlingUnitsBL;

@Autowired
 private  PickingCandidateService pickingCandidateService;

 private  String PARAM_M_HU_PI_ID;

@Param(parameterName = PARAM_M_HU_PI_ID, mandatory = true)
 private  I_M_HU_PI targetHUPI;


@Override
public String doIt(){
    final List<I_M_HU> fromHUs = getSelectedPickingSlotTopLevelHUs();
    final IAllocationSource source = HUListAllocationSourceDestination.of(fromHUs).setDestroyEmptyHUs(true);
    final IHUProducerAllocationDestination destination = createHUProducer();
    HULoader.of(source, destination).setAllowPartialUnloads(false).setAllowPartialLoads(false).unloadAllFromSource();
    // If the source HU was destroyed, then "remove" it from picking slots
    final ImmutableSet<HuId> destroyedHUIds = fromHUs.stream().filter(handlingUnitsBL::isDestroyedRefreshFirst).map(I_M_HU::getM_HU_ID).map(HuId::ofRepoId).collect(ImmutableSet.toImmutableSet());
    if (!destroyedHUIds.isEmpty()) {
        pickingCandidateService.inactivateForHUIds(destroyedHUIds);
    }
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final List<PickingSlotRow> pickingSlotRows = getSelectedPickingSlotRows();
    if (pickingSlotRows.size() <= 1) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select more than one HU");
    }
    final Set<PickingSlotRowId> rootRowIds = getRootRowIdsForSelectedPickingSlotRows();
    if (rootRowIds.size() > 1) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("all selected HU rows shall be from one picking slot");
    }
    for (final PickingSlotRow pickingSlotRow : pickingSlotRows) {
        if (!pickingSlotRow.isPickedHURow()) {
            return ProcessPreconditionsResolution.rejectWithInternalReason("select an HU");
        }
        if (!pickingSlotRow.isTopLevelHU()) {
            return ProcessPreconditionsResolution.rejectWithInternalReason("select an top level HU");
        }
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


public IHUProducerAllocationDestination createHUProducer(){
    final PickingSlotRow pickingRow = getRootRowForSelectedPickingSlotRows();
    return createNewHUProducer(pickingRow, targetHUPI);
}


}