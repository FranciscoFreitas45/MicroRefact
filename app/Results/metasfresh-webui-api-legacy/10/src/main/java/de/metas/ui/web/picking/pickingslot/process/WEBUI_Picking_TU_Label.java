package de.metas.ui.web.picking.pickingslot.process;
 import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_PICK_SOMETHING;
import de.metas.ui.web.picking.PickingConstants.MSG_WEBUI_PICKING_SELECT_PICKED_HU;
import org.adempiere.model.InterfaceWrapperHelper.load;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.report.HUReportService;
import de.metas.handlingunits.report.HUToReportWrapper;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.picking.pickingslot.PickingSlotRow;
import lombok.NonNull;
public class WEBUI_Picking_TU_Label extends PickingSlotViewBasedProcess{


@Override
public String doIt(){
    final PickingSlotRow rowToProcess = getSingleSelectedRow();
    final I_M_HU hu = load(rowToProcess.getHuId(), I_M_HU.class);
    final HUToReportWrapper huToReport = HUToReportWrapper.of(hu);
    printPickingLabel(huToReport);
    return MSG_OK;
}


public void printPickingLabel(HUToReportWrapper huToReport){
    final HUReportService huReportService = HUReportService.get();
    huReportService.printPickingLabel(huToReport, false);
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
    return ProcessPreconditionsResolution.accept();
}


}