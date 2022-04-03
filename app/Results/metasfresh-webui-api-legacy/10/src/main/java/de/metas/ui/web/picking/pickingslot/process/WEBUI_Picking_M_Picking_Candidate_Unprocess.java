package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_NO_PROCESSED_RECORDS;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;
import org.springframework.beans.factory.annotation.Autowired;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import de.metas.ui.web.picking.pickingslot.PickingSlotViewFactory;
public class WEBUI_Picking_M_Picking_Candidate_Unprocess extends PickingSlotViewBasedProcess{

@Autowired
 private  PickingCandidateService pickingCandidateService;


@Override
public String doIt(){
    final PickingSlotRow rowToProcess = getSingleSelectedRow();
    final HuId huId = rowToProcess.getHuId();
    pickingCandidateService.unprocessForHUId(huId);
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
    if (!pickingSlotRow.isProcessed()) {
        return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_NO_PROCESSED_RECORDS));
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