package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_NO_UNPROCESSED_RECORDS;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_PICK_SOMETHING;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.ImmutableSet;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.inoutcandidate.api.ShipmentScheduleId;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFactory;
public class WEBUI_Picking_M_Picking_Candidate_Process extends PickingSlotViewBasedProcess{

@Autowired
 private  PickingCandidateService pickingCandidateService;


@Override
public String doIt(){
    final PickingSlotRow rowToProcess = getSingleSelectedRow();
    final ShipmentScheduleId shipmentScheduleId = null;
    pickingCandidateService.processForHUIds(ImmutableSet.of(rowToProcess.getHuId()), shipmentScheduleId);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    if (!pickingSlotRow.isPickedHURow()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKED_HU));
    }
    if (!pickingSlotRow.isTopLevelHU()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_PICKED_HU));
    }
    if (pickingSlotRow.getHuQtyCU().signum() <= 0) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_PICK_SOMETHING));
    }
    if (pickingSlotRow.isProcessed()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_NO_UNPROCESSED_RECORDS));
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    invalidatePickingSlotsView();
    invalidatePackablesView();
}


}