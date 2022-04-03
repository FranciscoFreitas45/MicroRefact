package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_SOURCE_HU;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
public class WEBUI_Picking_M_Source_HU_Delete extends PickingSlotViewBasedProcess{

 private  boolean sourceWasDeleted;


@Override
public String doIt(){
    final PickingSlotRow rowToProcess = getSingleSelectedRow();
    final HuId huId = rowToProcess.getHuId();
    this.sourceWasDeleted = SourceHUsService.get().deleteSourceHuMarker(huId);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PickingSlotRow pickingSlotRow = getSingleSelectedRow();
    if (!pickingSlotRow.isPickingSourceHURow()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_SOURCE_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    if (!success) {
        return;
    }
    if (sourceWasDeleted) {
        // unselect the row we just deleted the record of, to avoid an 'EntityNotFoundException'
        invalidatePickingSlotsView();
    }
}


}