package de.metas.ui.web.pporder.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_SOURCE_HU;
import de.metas.handlingunits.HuId;
import de.metas.handlingunits.sourcehu.SourceHUsService;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLineRow;
public class WEBUI_PP_Order_M_Source_HU_Delete extends WEBUI_PP_Order_Templateimplements IProcessPrecondition{


@Override
public String doIt(){
    final PPOrderLineRow rowToProcess = getSingleSelectedRow();
    final HuId huId = rowToProcess.getHuId();
    // unselect the row we just deleted the record of, to avoid an 'EntityNotFoundException'
    final boolean sourceWasDeleted = SourceHUsService.get().deleteSourceHuMarker(huId);
    if (sourceWasDeleted) {
        getView().invalidateAll();
    }
    invalidateView();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final PPOrderLineRow pickingSlotRow = getSingleSelectedRow();
    if (!pickingSlotRow.isSourceHU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason(msgBL.getTranslatableMsgText(MSG_WEBUI_PICKING_SELECT_SOURCE_HU));
    }
    return ProcessPreconditionsResolution.accept();
}


}