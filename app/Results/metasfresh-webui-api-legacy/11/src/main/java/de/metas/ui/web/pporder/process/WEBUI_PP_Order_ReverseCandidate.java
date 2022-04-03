package de.metas.ui.web.pporder.process;
 import org.eevolution.model.X_PP_Order_BOMLine;
import de.metas.handlingunits.model.I_PP_Order_Qty;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyBL;
import de.metas.handlingunits.pporder.api.IHUPPOrderQtyDAO;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.util.Services;
import de.metas.util.StringUtils;
public class WEBUI_PP_Order_ReverseCandidate extends WEBUI_PP_Order_Templateimplements IProcessPrecondition{

 private  IHUPPOrderQtyBL huPPOrderQtyBL;

 private  IHUPPOrderQtyDAO huPPOrderQtyDAO;


@Override
public String doIt(){
    final int ppOrderQtyId = getSingleSelectedRow().getPP_Order_Qty_ID();
    final I_PP_Order_Qty candidate = huPPOrderQtyDAO.retrieveById(ppOrderQtyId);
    huPPOrderQtyBL.reverseDraftCandidate(candidate);
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        final String internalReason = StringUtils.formatMessage("Select one line");
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    final PPOrderLineRow row = getSingleSelectedRow();
    if (row.isSourceHU()) {
        return ProcessPreconditionsResolution.rejectWithInternalReason("Not available for source HU line");
    }
    if (row.getPP_Order_Qty_ID() <= 0) {
        final String internalReason = StringUtils.formatMessage("Not an issue/receipt line");
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    if (row.isProcessed() && !(X_PP_Order_BOMLine.ISSUEMETHOD_IssueOnlyForReceived.equals(row.getIssueMethod()))) {
        final String internalReason = StringUtils.formatMessage("Only not processed");
        return ProcessPreconditionsResolution.rejectWithInternalReason(internalReason);
    }
    return ProcessPreconditionsResolution.accept();
}


@Override
public void postProcess(boolean success){
    getView().invalidateAll();
}


}