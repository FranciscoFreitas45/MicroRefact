package de.metas.ui.web.pporder.process;
 import de.metas.ui.web.handlingunits.WEBUI_HU_Constants.MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import de.metas.handlingunits.allocation.transfer.HUTransformService;
import de.metas.handlingunits.allocation.transfer.HUTransformService.HUsToNewTUsRequest;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.pporder.api.IHUPPOrderBL;
import de.metas.i18n.IMsgBL;
import de.metas.i18n.ITranslatableString;
import de.metas.material.planning.pporder.PPOrderId;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.util.Services;
public class WEBUI_PP_Order_HUEditor_IssueTUs extends WEBUI_PP_Order_HUEditor_ProcessBaseimplements IProcessPrecondition{

@Param(parameterName = "QtyTU", mandatory = true)
 private  int qtyTUs;


@Override
public String doIt(){
    final HUEditorRow row = getSingleSelectedRow();
    final I_M_HU sourceLUorTU = row.getM_HU();
    final HUsToNewTUsRequest request = HUsToNewTUsRequest.forSourceHuAndQty(sourceLUorTU, qtyTUs);
    final List<I_M_HU> extractedTUs = HUTransformService.newInstance().husToNewTUs(request);
    if (extractedTUs.isEmpty()) {
        throw new AdempiereException("@NoSelection@");
    }
    final PPOrderLinesView ppOrderView = getPPOrderView().get();
    final PPOrderId ppOrderId = ppOrderView.getPpOrderId();
    Services.get(IHUPPOrderBL.class).createIssueProducer(ppOrderId).createIssues(extractedTUs);
    getView().invalidateAll();
    ppOrderView.invalidateAll();
    return MSG_OK;
}


@Override
public ProcessPreconditionsResolution checkPreconditionsApplicable(){
    if (!getSelectedRowIds().isSingleDocumentId()) {
        return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
    }
    final boolean anyHuMatches = retrieveSelectedAndEligibleHUEditorRows().anyMatch(huRow -> huRow.isTU() || huRow.isTopLevel());
    if (anyHuMatches) {
        return ProcessPreconditionsResolution.accept();
    }
    final ITranslatableString reason = Services.get(IMsgBL.class).getTranslatableMsgText(MSG_WEBUI_SELECT_ACTIVE_UNSELECTED_HU);
    return ProcessPreconditionsResolution.reject(reason);
}


}