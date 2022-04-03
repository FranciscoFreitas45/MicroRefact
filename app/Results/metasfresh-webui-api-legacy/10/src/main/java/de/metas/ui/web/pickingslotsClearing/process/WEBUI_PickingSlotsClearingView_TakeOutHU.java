package de.metas.ui.web.pickingslotsClearing.process;
 import org.adempiere.model.InterfaceWrapperHelper.save;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHUContextFactory;
import de.metas.handlingunits.IHUStatusBL;
import de.metas.handlingunits.IHUWarehouseDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_Locator;
import de.metas.handlingunits.movement.api.IHUMovementBL;
import de.metas.handlingunits.picking.IHUPickingSlotBL;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.pickingslotsClearing.PickingSlotsClearingView;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Check;
import de.metas.util.Services;
public class WEBUI_PickingSlotsClearingView_TakeOutHU extends PickingSlotsClearingViewBasedProcessimplements IProcessPrecondition{

 private  IHUPickingSlotBL huPickingSlotBL;

 private  IHUWarehouseDAO huWarehouseDAO;

 private  IHUMovementBL huMovementBL;

 private  IHUContextFactory huContextFactory;

 private  IHUStatusBL huStatusBL;

@Autowired
 private  PickingCandidateService pickingCandidateService;

 private  List<HUExtractedFromPickingSlotEvent> husExtractedEvents;


@Override
public String doIt(){
    // 
    // Get the HU
    final PickingSlotRow huRow = getSingleSelectedPickingSlotRow();
    Check.assume(huRow.isTopLevelHU(), "row {} shall be a top level HU", huRow);
    final I_M_HU hu = InterfaceWrapperHelper.load(huRow.getHuId(), I_M_HU.class);
    final String huStatus = hu.getHUStatus();
    // 
    // Remove the HU from it's picking slot
    huPickingSlotBL.removeFromPickingSlotQueueRecursivelly(hu);
    // 
    // Make sure the HU has the BPartner/Location of the picking slot
    final PickingSlotRow pickingSlotRow = getRootRowForSelectedPickingSlotRows();
    if (pickingSlotRow.getBPartnerId() > 0) {
        hu.setC_BPartner_ID(pickingSlotRow.getBPartnerId());
        hu.setC_BPartner_Location_ID(pickingSlotRow.getBPartnerLocationId());
        InterfaceWrapperHelper.save(hu);
    }
    // 
    // Move the HU to an after picking locator
    final I_M_Locator afterPickingLocator = huWarehouseDAO.suggestAfterPickingLocator(hu.getM_Locator_ID());
    if (afterPickingLocator == null) {
        throw new AdempiereException("No after picking locator found for locatorId=" + hu.getM_Locator_ID());
    }
    if (afterPickingLocator.getM_Locator_ID() != hu.getM_Locator_ID()) {
        huMovementBL.moveHUsToLocator(ImmutableList.of(hu), afterPickingLocator);
        // 
        // FIXME: workaround to restore HU's HUStatus (i.e. which was changed from Picked to Active by the moveHUsToLocator() method, indirectly).
        // See https://github.com/metasfresh/metasfresh-webui-api/issues/678#issuecomment-344876035, that's the stacktrace where the HU status was set to Active.
        InterfaceWrapperHelper.refresh(hu, ITrx.TRXNAME_ThreadInherited);
        if (!Objects.equal(huStatus, hu.getHUStatus())) {
            final IHUContext huContext = huContextFactory.createMutableHUContext();
            huStatusBL.setHUStatus(huContext, hu, huStatus);
            save(hu);
        }
    }
    // 
    // Inactive all those picking candidates
    pickingCandidateService.inactivateForHUId(HuId.ofRepoId(hu.getM_HU_ID()));
    husExtractedEvents.add(HUExtractedFromPickingSlotEvent.builder().huId(hu.getM_HU_ID()).bpartnerId(hu.getC_BPartner_ID()).bpartnerLocationId(hu.getC_BPartner_Location_ID()).build());
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
    if (selectedRowIds.isEmpty()) {
        return ProcessPreconditionsResolution.rejectBecauseNoSelection();
    }
    if (!selectedRowIds.isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PickingSlotRow row = getSingleSelectedPickingSlotRow();
    if (!row.isTopLevelHU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("select a top level HU");
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    // 
    // Invalidate the views
    // Expectation: the HU shall disappear from picking slots view (left side) and shall appear on after picking HUs view (right side).
    final PickingSlotsClearingView pickingSlotsClearingView = getPickingSlotsClearingView();
    invalidateView(pickingSlotsClearingView.getViewId());
    // 
    husExtractedEvents.forEach(pickingSlotsClearingView::handleEvent);
}


}