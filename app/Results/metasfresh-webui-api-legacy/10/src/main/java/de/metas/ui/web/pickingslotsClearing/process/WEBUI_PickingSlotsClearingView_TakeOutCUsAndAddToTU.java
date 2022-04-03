package de.metas.ui.web.pickingslotsClearing.process;
 import org.adempiere.model.InterfaceWrapperHelper.load;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.exceptions.FillMandatoryException;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IMutableHUContext;
import de.metas.handlingunits.allocation.IAllocationDestination;
import de.metas.handlingunits.allocation.IAllocationSource;
import de.metas.handlingunits.allocation.impl.HUListAllocationSourceDestination;
import de.metas.handlingunits.allocation.impl.HULoader;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.storage.EmptyHUListener;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.util.Check;
import de.metas.util.Services;
public class WEBUI_PickingSlotsClearingView_TakeOutCUsAndAddToTU extends PickingSlotsClearingViewBasedProcessimplements IProcessDefaultParametersProvider,IProcessPrecondition{

@Autowired
 private  PickingCandidateService pickingCandidateService;

 private  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU)
 private  BigDecimal qtyCU;

 private  String PARAM_IsAllQty;

@Param(parameterName = PARAM_IsAllQty)
 private  boolean isAllQty;


public List<I_M_HU> getSourceCUs(){
    return getSelectedPickingSlotRows().stream().peek(huRow -> Check.assume(huRow.isCU(), "row {} shall be a CU", huRow)).map(PickingSlotRow::getHuId).distinct().map(huId -> load(huId, I_M_HU.class)).collect(ImmutableList.toImmutableList());
}


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_IsAllQty.equals(parameter.getColumnName())) {
        return getSelectedPickingSlotRows().size() > 1;
    } else if (PARAM_QtyCU.equals(parameter.getColumnName())) {
        final List<PickingSlotRow> pickingSlotRows = getSelectedPickingSlotRows();
        if (pickingSlotRows.size() != 1) {
            return DEFAULT_VALUE_NOTAVAILABLE;
        }
        return pickingSlotRows.get(0).getHuQtyCU();
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    final List<I_M_HU> fromCUs = getSourceCUs();
    final IAllocationSource source = HUListAllocationSourceDestination.of(fromCUs).setDestroyEmptyHUs(true);
    final IAllocationDestination destination = HUListAllocationSourceDestination.of(getTargetTU());
    final HULoader huLoader = HULoader.of(source, destination).setAllowPartialUnloads(false).setAllowPartialLoads(false);
    // 
    // Unload CU/CUs and Load to selected TU
    final List<Integer> huIdsDestroyedCollector = new ArrayList<>();
    if (fromCUs.size() == 1) {
        huLoader.load(prepareUnloadRequest(fromCUs.get(0), getQtyCU()).setForceQtyAllocation(true).addEmptyHUListener(EmptyHUListener.doBeforeDestroyed(hu -> huIdsDestroyedCollector.add(hu.getM_HU_ID()))).create());
    } else {
        final IHUContextFactory huContextFactory = Services.get(IHUContextFactory.class);
        final IMutableHUContext huContext = huContextFactory.createMutableHUContext();
        huContext.addEmptyHUListener(EmptyHUListener.doBeforeDestroyed(hu -> huIdsDestroyedCollector.add(hu.getM_HU_ID())));
        huLoader.unloadAllFromSource(huContext);
    }
    // Remove from picking slots all destroyed HUs
    pickingCandidateService.inactivateForHUIds(HuId.fromRepoIds(huIdsDestroyedCollector));
    return MSG_OK;
}


public I_M_HU getTargetTU(){
    final HUEditorRow huRow = getSingleSelectedPackingHUsRow();
    Check.assume(huRow.isTU(), "row {} shall be a TU", huRow);
    return huRow.getM_HU();
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    // 
    // Validate the picking slots clearing selected row (left side)
    // i.e. the ones which we will Take Out
    final List<PickingSlotRow> pickingSlotRows = getSelectedPickingSlotRows();
    if (pickingSlotRows.isEmpty()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select some picking slots CUs");
    }
    for (final PickingSlotRow pickingSlotRow : pickingSlotRows) {
        if (!pickingSlotRow.isCU()) {
            return ProcessPreconditionsResolution.rejectWithInternalReason("select a CU");
        }
    }
    // 
    // Validate the packing HUs selected row (right side)
    // i.e. the HU where we will pack to
    if (!isSingleSelectedPackingHUsRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select one and only one TU to pack to");
    }
    final HUEditorRow packingHURow = getSingleSelectedPackingHUsRow();
    if (!packingHURow.isTU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a TU to pack too");
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


public BigDecimal getQtyCU(){
    if (isAllQty) {
        return getSingleSelectedPickingSlotRow().getHuQtyCU();
    } else {
        final BigDecimal qtyCU = this.qtyCU;
        if (qtyCU == null || qtyCU.signum() <= 0) {
            throw new FillMandatoryException(PARAM_QtyCU);
        }
        return qtyCU;
    }
}


}