package de.metas.ui.web.pickingslotsClearing.process;
 import java.math.BigDecimal;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.I_C_UOM;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.allocation.IAllocationSource;
import de.metas.handlingunits.allocation.IHUProducerAllocationDestination;
import de.metas.handlingunits.allocation.impl.HUListAllocationSourceDestination;
import de.metas.handlingunits.allocation.impl.HULoader;
import de.metas.handlingunits.allocation.transfer.impl.LUTUProducerDestination;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.handlingunits.storage.IHUStorage;
import de.metas.handlingunits.storage.IHUStorageFactory;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.util.Services;
import lombok.NonNull;
public class WEBUI_PickingSlotsClearingView_TakeOutHUAndAddToNewHU extends PickingSlotsClearingViewBasedProcessimplements IProcessDefaultParametersProvider,IProcessPrecondition{

 private  IHandlingUnitsBL handlingUnitsBL;

@Autowired
 private  PickingCandidateService pickingCandidateService;

 private  String PARAM_M_HU_PI_ID;

@Param(parameterName = PARAM_M_HU_PI_ID, mandatory = true)
 private  I_M_HU_PI targetHUPI;

 private  String PARAM_QtyCU;

@Param(parameterName = PARAM_QtyCU, mandatory = true)
 private  BigDecimal qtyCU;


@Override
public Object getParameterDefaultValue(IProcessDefaultParameter parameter){
    if (PARAM_QtyCU.equals(parameter.getColumnName())) {
        final I_M_HU fromHU = getSingleSelectedPickingSlotTopLevelHU();
        return retrieveQtyCU(fromHU);
    } else {
        return DEFAULT_VALUE_NOTAVAILABLE;
    }
}


@Override
public String doIt(){
    if (qtyCU == null || qtyCU.signum() <= 0) {
        throw new FillMandatoryException(PARAM_QtyCU);
    }
    final I_M_HU fromHU = getSingleSelectedPickingSlotTopLevelHU();
    final IAllocationSource source = HUListAllocationSourceDestination.of(fromHU).setDestroyEmptyHUs(true);
    final IHUProducerAllocationDestination destination = createHUProducer(fromHU);
    HULoader.of(source, destination).setAllowPartialUnloads(false).setAllowPartialLoads(false).load(prepareUnloadRequest(fromHU, qtyCU).setForceQtyAllocation(true).create());
    // If the source HU was destroyed, then "remove" it from picking slots
    if (handlingUnitsBL.isDestroyedRefreshFirst(fromHU)) {
        pickingCandidateService.inactivateForHUId(HuId.ofRepoId(fromHU.getM_HU_ID()));
    }
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!isSingleSelectedPickingSlotRow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select one and only one picking slots HU");
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedPickingSlotRow();
    if (!pickingSlotRow.isPickedHURow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select an HU");
    }
    if (!pickingSlotRow.isTopLevelHU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a top level HU");
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


public IHUProducerAllocationDestination createHUProducer(I_M_HU fromHU){
    final PickingSlotRow pickingRow = getRootRowForSelectedPickingSlotRows();
    final IHUStorageFactory storageFactory = Services.get(IHandlingUnitsBL.class).getStorageFactory();
    final IHUStorage storage = storageFactory.getStorage(fromHU);
    final ProductId singleProductId = storage.getSingleProductIdOrNull();
    final I_C_UOM uom = Services.get(IProductBL.class).getStockUOM(singleProductId);
    final LUTUProducerDestination lutuProducerDestination = createNewHUProducer(pickingRow, targetHUPI);
    lutuProducerDestination.addCUPerTU(singleProductId, qtyCU, uom);
    return lutuProducerDestination;
}


}